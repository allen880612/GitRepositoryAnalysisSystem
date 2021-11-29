package domain;

import java.util.UUID;

public class GitRepository {
    private String id;
    private String repoName;
    private String ownerName;
    private String projectID;

    public GitRepository(String repoName, String ownerName, String projectID) {
        this.id = UUID.randomUUID().toString();
        this.repoName = repoName;
        this.ownerName = ownerName;
        this.projectID = projectID;

    }

    public GitRepository(String id, String repoName, String ownerName, String projectID) {
        this.id = id;
        this.repoName = repoName;
        this.ownerName = ownerName;
        this.projectID = projectID;
    }

    public String getId() {
        return id;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getProjectID() {
        return projectID;
    }



}
