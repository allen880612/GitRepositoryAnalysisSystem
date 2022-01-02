package usecase.project;

public interface CreateProjectInput {
    void setName(String name);
    String getName();

    void setDescription(String description);
    String getDescription();

    void setSonarProjectID(String sonarProjectID);

    String getSonarProjectID();

    String getGitRepositoryID();

    void setGitRepositoryID(String gitRepositoryID);

}
