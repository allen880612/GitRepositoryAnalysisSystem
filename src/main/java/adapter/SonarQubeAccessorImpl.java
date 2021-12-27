package adapter;

import adapter.gson.SonarBugListGsonAdapter;
import com.google.gson.Gson;
import domain.SonarProject;
import dto.SonarBugInfoDTO;
import dto.SonarBugListDTO;
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
    public SonarBugListDTO getSonarBugs() {
        String api = "http://%s/api/issues/search?projectKeys=%s&types=BUG&ps=10";
        String apiFormatted = String.format(api, hostUrl, projectKey);
        SonarBugListDTO sonarBugListDto = new SonarBugListDTO();

        try {
            JSONObject response = getRequester.httpsGet(apiFormatted).getJSONObject(0);
            sonarBugListDto = parseBugList(response);
        } catch (IOException e) {
            sonarBugListDto.setSuccessful(false);
        }
        return sonarBugListDto;
    }

    private SonarBugListDTO parseBugList(JSONObject response) {
        SonarBugListGsonAdapter adapter = new Gson().fromJson(response.toString() , SonarBugListGsonAdapter.class);
        SonarBugListDTO sonarBugListDto = new SonarBugListDTO();

        sonarBugListDto.setCount(adapter.getTotal());
        sonarBugListDto.setEffortTotal(adapter.getEffortTotal());
        for ( SonarBugListGsonAdapter.BugInfo bugInfo: adapter.getIssues()) {
            SonarBugInfoDTO bugInfoDto = new SonarBugInfoDTO();
            bugInfoDto.setTitle(bugInfo.getMessage());
            bugInfoDto.setEffort(bugInfo.getEffort());
            bugInfoDto.setComponent(bugInfo.getComponent());
            bugInfoDto.setSeverity(bugInfo.getSeverity());
            String url = "http://%s/project/issues?id=%s&open=%s&resolved=false&types=BUG";
            String redirectUrl = String.format(url, hostUrl, projectKey, bugInfo.getKey());
            bugInfoDto.setRedirectUrl(redirectUrl);

            sonarBugListDto.addBugs(bugInfoDto);
        }
        return sonarBugListDto;
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
