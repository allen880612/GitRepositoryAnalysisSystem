package usecase.sonarproject;

import domain.GitRepository;
import domain.SonarProject;

public interface SonarProjectRepository {
    SonarProject getSonarProjectById(String id);
    void createSonarProject(SonarProject sonarProject);
    void deleteSonarProject(String id);
}
