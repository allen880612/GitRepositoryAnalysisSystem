package usecase.project;

import domain.Project;

public interface ProjectRepository {
    Project getProjectWithRepositoryById(String id);
    Project getProjectWithoutRepositoryById(String id);
    void createProject(Project project);
    boolean deleteProject(String id);
}
