package usecase.sonarproject;

import domain.GitRepository;
import domain.SonarProject;

import java.sql.SQLException;

public interface SonarProjectRepository {
    SonarProject getSonarProjectBySonarProjectId(String id);
    SonarProject getSonarProjectByProjectId(String projectId);
    void createSonarProject(SonarProject sonarProject, String projectId) throws SQLException;
    void deleteSonarProject(String id);
}
