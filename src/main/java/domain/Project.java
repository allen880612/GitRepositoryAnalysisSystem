package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {
    private String id;
    private String name;
    private String description;
    private String startTime;
    private List<String> gitRepositories;//感覺這行可以刪掉?
    private String sonarProjectID;
    private String gitRepositoryID;

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSonarProjectID() {
        return sonarProjectID;
    }

    public void setSonarProjectID(String sonarProjectID) {
        this.sonarProjectID = sonarProjectID;
    }

    public String getGitRepositoryID() {
        return gitRepositoryID;
    }

    public void setGitRepositoryID(String gitRepositoryID) {
        this.gitRepositoryID = gitRepositoryID;
    }




    public Project(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.gitRepositories = new ArrayList<>();
    }

    public Project(String name, String description, String startTime, String gitRepoID,String sonarProjectID){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.gitRepositoryID = gitRepoID;
        this.sonarProjectID = sonarProjectID;
    }

    public Project(String id, String name, String description, String startTime, List<String> gitRepositories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.gitRepositories = gitRepositories;
    }

    public void addGitRepository(String id){
        //可改進:use eventbus
        gitRepositories.add(id);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public List<String> getGitRepositories() {
        return gitRepositories;
    }

    public void setGitRepositories(List<String> gitRepositories) {
        this.gitRepositories = gitRepositories;
    }

    public void removeGitRepository(String id){
        this.gitRepositories.remove(id);
    }
}
