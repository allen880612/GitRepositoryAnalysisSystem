package dto;

public class SonarIssueInfoDTO {
    private String type;
    private String title;
    private String component;
    private String severity;
    private String redirectUrl;
    private String effort;

    public void setType(String type) { this.type = type; }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setComponent(String component) {
        this.component = component;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    public void setEffort(String effort) {
        this.effort = effort;
    }
}
