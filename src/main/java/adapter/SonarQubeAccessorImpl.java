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

public class SonarQubeAccessorImpl implements SonarQubeAccessor {
    private URLRequester getRequester;

    private String hostUrl;
    private String projectKey;
    private String token;

    public SonarQubeAccessorImpl(SonarProject sonarProject) {
        this.hostUrl = sonarProject.getHostUrl();
        this.projectKey = sonarProject.getProjectKey();
        this.token = sonarProject.getToken();

        this.getRequester = new HttpRequester();
        initializeAuthorization();
    }

    public SonarQubeAccessorImpl(String hostUrl, String projectKey, String token) {
        this.hostUrl = hostUrl;
        this.projectKey = projectKey;
        this.token = token;

        this.getRequester = new HttpRequester();
        getRequester.addHTTPSGetProperty("Content-Type", "application/json");
        initializeAuthorization();
    }

    private void initializeAuthorization() {
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
        String api = "http://%s/api/measures/search?projectKeys=%s&metricKeys=bugs,code_smells,coverage,duplicated_lines_density";
        String apiFormatted = String.format(api, hostUrl, projectKey);
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
            if (key.equals("bugs")) {
                sonarQubeInfoDto.setBugs(measureJson.getInt("value"));
            } else if (key.equals("code_smells")) {
                sonarQubeInfoDto.setCodeSmell(measureJson.getInt("value"));
            } else if (key.equals("coverage")) {
                sonarQubeInfoDto.setCoverage(measureJson.getDouble("value"));
            } else if (key.equals("duplicated_lines_density")) {
                sonarQubeInfoDto.setDuplication(measureJson.getDouble("value"));
            }
        }
        return sonarQubeInfoDto;
    }
}
