package usecase.sonarproject;

public interface CreateSonarProjectInput {
    String getHostUrl();

    void setHostUrl(String hostUrl);

    String getToken();

    void setToken(String token);

    String getProjectKey();

    void setProjectKey(String projectKey);

    String getProjectId();

    void setProjectId(String projectID);
}
