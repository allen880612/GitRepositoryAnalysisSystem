package usecase.project;

import domain.Project;
import usecase.gitrepository.GitRepositoryRepository;
import usecase.project.CreateProjectInput;
import usecase.project.CreateProjectOutput;
import usecase.project.ProjectRepository;
import usecase.sonarproject.SonarProjectRepository;

import java.sql.SQLException;

public class CreateProjectUseCase {
    private ProjectRepository projectRepository;

    public CreateProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(CreateProjectInput input, CreateProjectOutput output){
        Project project = new Project(input.getName(), input.getDescription());
        try {
            projectRepository.createProject(project);
            output.setIsSuccessful(true);
        }catch (SQLException e){
            e.printStackTrace();
            output.setIsSuccessful(false);
        }
        output.setId(project.getId());
        output.setName(project.getName());

    }
}
