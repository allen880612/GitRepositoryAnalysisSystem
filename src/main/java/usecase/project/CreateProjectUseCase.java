package usecase.project;

import domain.Project;
import usecase.gitrepository.GitRepositoryRepository;
import usecase.project.CreateProjectInput;
import usecase.project.CreateProjectOutput;
import usecase.project.ProjectRepository;
import usecase.sonarproject.SonarProjectRepository;

public class CreateProjectUseCase {
    private ProjectRepository projectRepository;

    public CreateProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void execute(CreateProjectInput input, CreateProjectOutput output){//還沒完成，先下班晚點繼續
        Project project = new Project(input.getName(), input.getDescription());
        projectRepository.createProject(project);

        output.setId(project.getId());
        output.setName(project.getName());

    }
}
