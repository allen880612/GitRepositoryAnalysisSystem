package adapter;

import adapter.gson.SonarIssueListGsonAdapter;
import com.google.gson.Gson;
import domain.SonarProject;
import dto.SonarIssueInfoDTO;
import dto.SonarIssueListDTO;
import dto.SonarQubeInfoDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import usecase.SonarQubeAccessor;
import usecase.URLRequester;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SonarQubeAccessorImpl implements SonarQubeAccessor {
    private URLRequester getRequester;

    private String hostUrl;
    private String projectKey;
    private String token;
    private Map<String, String> ratingMap;

    public SonarQubeAccessorImpl(SonarProject sonarProject) {
        this.hostUrl = sonarProject.getHostUrl();
        this.projectKey = sonarProject.getProjectKey();
        this.token = sonarProject.getToken();
        initialize();
    }

    public SonarQubeAccessorImpl(String hostUrl, String projectKey, String token) {
        this.hostUrl = hostUrl;
        this.projectKey = projectKey;
        this.token = token;
        initialize();
    }

    private void initialize() {
        initializeAuthorization();
        initializeRatingMap();
    }

    private void initializeRatingMap() {
        ratingMap = new HashMap<String, String>();
        ratingMap.put("1.0", "A");
        ratingMap.put("2.0", "B");
        ratingMap.put("3.0", "C");
        ratingMap.put("4.0", "D");
        ratingMap.put("5.0", "E");
    }

    private void initializeAuthorization() {
        this.getRequester = new HttpRequester();
        getRequester.addHTTPSGetProperty("Content-Type", "application/json");
        String encoded = Base64.getEncoder().encodeToString((token+":").getBytes(StandardCharsets.UTF_8));
        this.getRequester.addHTTPSGetProperty("Authorization", "Basic " + encoded);
    }

    @Override
    public boolean isSonarProjectValid() {
        String api = "http://%s/api/project_analyses/search?project=%s";
        String apiFormatted = String.format(api, hostUrl, projectKey);

        try {
            JSONObject response = getRequester.httpsGet(apiFormatted).getJSONObject(0);
            JSONArray analyses = response.getJSONArray("analyses");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public SonarQubeInfoDTO getSonarInfo() {
        final String METRIC_KEYS = "bugs,code_smells,coverage,duplicated_lines_density,vulnerabilities,security_hotspots," +
                                   "security_rating,security_review_rating,new_maintainability_rating,reliability_rating";
        String api = "http://%s/api/measures/search?projectKeys=%s&metricKeys=%s";
        String apiFormatted = String.format(api, hostUrl, projectKey,METRIC_KEYS);
        SonarQubeInfoDTO sonarQubeInfoDto = new SonarQubeInfoDTO();

        try {
            JSONObject response = getRequester.httpsGet(apiFormatted).getJSONObject(0);
            JSONArray measures = response.getJSONArray("measures");
            sonarQubeInfoDto = parseMeasures(measures);
        } catch (IOException e) {
            sonarQubeInfoDto.setSuccessful(false);
        }
        return sonarQubeInfoDto;
    }

    @Override
    public SonarIssueListDTO getSonarIssueList() {
        String api = "http://%s/api/issues/search?projectKeys=%s&ps=100";
        String apiFormatted = String.format(api, hostUrl, projectKey);
        SonarIssueListDTO sonarIssueListDto = new SonarIssueListDTO();

        try {
            JSONObject response = getRequester.httpsGet(apiFormatted).getJSONObject(0);
            sonarIssueListDto = parseBugList(response);
        } catch (IOException e) {
            sonarIssueListDto.setSuccessful(false);
        }
        return sonarIssueListDto;
    }

    private SonarIssueListDTO parseBugList(JSONObject response) {
        SonarIssueListGsonAdapter adapter = new Gson().fromJson(response.toString() , SonarIssueListGsonAdapter.class);
        SonarIssueListDTO sonarIssueListDto = new SonarIssueListDTO();

        sonarIssueListDto.setCount(adapter.getTotal());
        sonarIssueListDto.setEffortTotal(adapter.getEffortTotal());
        for ( SonarIssueListGsonAdapter.IssueInfo issueInfo : adapter.getIssues()) {
            SonarIssueInfoDTO bugInfoDto = new SonarIssueInfoDTO();
            bugInfoDto.setType(issueInfo.getType());
            bugInfoDto.setTitle(issueInfo.getMessage());
            bugInfoDto.setEffort(issueInfo.getEffort());
            bugInfoDto.setComponent(issueInfo.getComponent());
            bugInfoDto.setSeverity(issueInfo.getSeverity());
            String url = "http://%s/project/issues?id=%s&open=%s&resolved=false&types=%s";
            String redirectUrl = String.format(url, hostUrl, projectKey, issueInfo.getKey(), issueInfo.getType());
            bugInfoDto.setRedirectUrl(redirectUrl);

            sonarIssueListDto.addBugs(bugInfoDto);
        }
        return sonarIssueListDto;
    }

    private SonarQubeInfoDTO parseMeasures(JSONArray measures) {
        SonarQubeInfoDTO sonarQubeInfoDto = new SonarQubeInfoDTO();
        for ( Object measure : measures) {
            if (!(measure instanceof JSONObject))
                continue;

            JSONObject measureJson = (JSONObject)measure;
            String key = measureJson.getString("metric");
            switch (key) {
                case "bugs":
                    sonarQubeInfoDto.setBugs(measureJson.getInt("value"));
                    break;
                case "code_smells":
                    sonarQubeInfoDto.setCodeSmell(measureJson.getInt("value"));
                    break;
                case "coverage":
                    sonarQubeInfoDto.setCoverage(measureJson.getDouble("value"));
                    break;
                case "duplicated_lines_density":
                    sonarQubeInfoDto.setDuplication(measureJson.getDouble("value"));
                    break;
                case "vulnerabilities":
                    sonarQubeInfoDto.setVulnerabilities(measureJson.getInt("value"));;
                    break;
                case "security_hotspots":
                    sonarQubeInfoDto.setSecurityHotspots(measureJson.getInt("value"));;
                    break;
                case "new_maintainability_rating":
                    JSONObject valueJson = (measureJson.getJSONObject("period"));
                    sonarQubeInfoDto.setMaintainabilityRating(ratingMap.get(valueJson.getString("value")));
                    break;
                case "reliability_rating":
                    sonarQubeInfoDto.setReliabilityRating(ratingMap.get(measureJson.getString("value")));
                    break;
                case "security_rating":
                    sonarQubeInfoDto.setSecurityRating(ratingMap.get(measureJson.getString("value")));
                    break;
                case "security_review_rating":
                    sonarQubeInfoDto.setSecurityReviewRating(ratingMap.get(measureJson.getString("value")));
                    break;
            }
        }
        return sonarQubeInfoDto;
    }
}
