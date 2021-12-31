package domain;

import java.util.UUID;

public class SonarProject {
    private String id;
    private String hostUrl;
    private String projectKey;
    private String token;

    public SonarProject(String hostUrl, String projectKey, String token) {
        this.id = UUID.randomUUID().toString();
        this.hostUrl = hostUrl;
        this.projectKey = projectKey;
        this.token = token;
    }

    public SonarProject(String id, String hostUrl, String projectKey, String token) {
        this.id = id;
        this.hostUrl = hostUrl;
        this.projectKey = projectKey;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }
}
