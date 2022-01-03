package usecase.sonarproject;

import domain.GitRepository;
import domain.SonarProject;

public interface SonarProjectRepository {
    SonarProject getSonarProjectBySonarProjectId(String id);
    SonarProject getSonarProjectByProjectId(String projectId);
    void createSonarProject(SonarProject sonarProject, String projectId);
    void deleteSonarProject(String id);
}
