package usecase.project;

import domain.Project;

import java.sql.SQLException;

public interface ProjectRepository {
    Project getProjectWithRepositoryById(String id);
    Project getProjectWithoutRepositoryById(String id);
    void createProject(Project project) throws SQLException;
    void deleteProject(String id) throws SQLException;
}
