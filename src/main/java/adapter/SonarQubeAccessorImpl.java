package adapter;

import domain.SonarProject;
import dto.SonarQubeInfoDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import usecase.SonarQubeAccessor;
import usecase.URLRequester;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

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

    @Override
    public List<SonarQubeInfoDTO> getSonarBugs() {
        return null;
    }
}
