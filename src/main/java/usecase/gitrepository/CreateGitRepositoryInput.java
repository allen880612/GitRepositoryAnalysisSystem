package usecase.gitrepository;

public interface CreateGitRepositoryInput {

    void setOwnerName(String ownerName);
    String getOwnerName();

    void setRepoName(String repoName);
    String getRepoName();

    void setProjectID(String projectID);
    String getProjectID();
}
