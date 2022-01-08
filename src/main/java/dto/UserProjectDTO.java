package dto;

import domain.Project;

public class UserProjectDTO {
    private String projectId;
    private String projectName;
    private String projectDescription;
    private String projectStartTime;
    private int gitRepoCount;

    public UserProjectDTO(){}

    public UserProjectDTO(Project project){
        this.projectId = project.getId();
        this.projectName = project.getName();
        this.projectDescription = project.getName();
        this.projectStartTime = project.getName();
        String gitRepoID = project.getGitRepositoryID();
        String sonarProjectID = project.getSonarProjectID();
        if(gitRepoID==null||gitRepoID.isEmpty()||sonarProjectID==null||sonarProjectID.isEmpty()){
            this.gitRepoCount = 0;
        }else {
            this.gitRepoCount = 1;
        }
    }
    public void setProjectId(String projectId) {this.projectId = projectId;}
    public void setProjectName(String projectName) {this.projectName = projectName;}
    public void setProjectDescription(String projectDescription) {this.projectDescription = projectDescription;}
    public void setProjectStartTime(String projectStartTime) {this.projectStartTime = projectStartTime;}
    public void setGitRepoCount(int gitRepoCount) {this.gitRepoCount = gitRepoCount;}
    public String getProjectId() {return projectId;}
    public String getProjectName() {return projectName;}
    public String getProjectDescription() {return projectDescription;}
    public String getProjectStartTime() {return projectStartTime;}
    public int getGitRepoCount() {return gitRepoCount;}
}
