package adapter.sonarproject;

import usecase.sonarproject.CreateSonarProjectInput;

public class CreateSonarProjectInputImpl implements CreateSonarProjectInput {
    private String projectId;
    private String hostUrl;
    private String projectKey;
    private String token;

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String getHostUrl() {
        return hostUrl;
    }

    @Override
    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    @Override
    public String getProjectKey() {
        return projectKey;
    }

    @Override
    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
