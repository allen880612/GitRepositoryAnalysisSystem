package adapter.project;

import usecase.project.CreateProjectInput;

public class CreateProjectInputImpl implements CreateProjectInput {
    private String name;
    private String description;
    private String sonarProjectID;
    private String gitRepositoryID;

    @Override
    public String getSonarProjectID() {
        return sonarProjectID;
    }

    @Override
    public void setSonarProjectID(String sonarProjectID) {
        this.sonarProjectID = sonarProjectID;
    }

    @Override
    public String getGitRepositoryID() {
        return gitRepositoryID;
    }

    @Override
    public void setGitRepositoryID(String gitRepositoryID) {
        this.gitRepositoryID = gitRepositoryID;
    }


    //Git URL
    //Sonar token ,projectKey, hosturl
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
