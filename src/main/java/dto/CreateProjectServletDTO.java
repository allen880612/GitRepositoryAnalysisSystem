package dto;

public class CreateProjectServletDTO {
    private String userId;
    private String projectName;
    private String projectDescription;
    private String githubUrl;
    private String sonarHost;
    private String sonarProjectKey;
    private String sonarToken;

    public CreateProjectServletDTO(String userId, String projectName, String projectDescription, String githubUrl,
                                   String sonarHost, String sonarProjectKey, String sonarToken) {
        this.userId = userId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.githubUrl = githubUrl;
        this.sonarHost = sonarHost;
        this.sonarProjectKey = sonarProjectKey;
        this.sonarToken = sonarToken;
    }

    public CreateProjectServletDTO() {}

    public String getUserId() { return userId; }
    public String getProjectName() { return projectName; }
    public String getProjectDescription() { return projectDescription; }
    public String getGithubUrl() { return githubUrl; }
    public String getSonarHost() { return sonarHost; }
    public String getSonarProjectKey() { return sonarProjectKey; }
    public String getSonarToken() { return sonarToken; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public void setProjectDescription(String projectDescription) { this.projectDescription = projectDescription; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public void setSonarHost(String sonarHost) { this.sonarHost = sonarHost; }
    public void setSonarProjectKey(String sonarProjectKey) { this.sonarProjectKey = sonarProjectKey; }
    public void setSonarToken(String sonarToken) { this.sonarToken = sonarToken; }
}
