package adapter.project;

import domain.Project;
import usecase.gitrepository.GitRepositoryRepository;
import usecase.project.CreateProjectInput;
import usecase.project.CreateProjectOutput;
import usecase.project.ProjectRepository;
import usecase.sonarproject.SonarProjectRepository;

public class CreateProjectUseCase {
    private ProjectRepository projectRepository;
    private GitRepositoryRepository gitRepositoryRepository;
    private SonarProjectRepository sonarProjectRepository;

    public CreateProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;

    }
    public CreateProjectUseCase(ProjectRepository projectRepository,GitRepositoryRepository gitRepositoryRepository
            ,SonarProjectRepository sonarProjectRepository) {
        this.projectRepository = projectRepository;
        this.gitRepositoryRepository = gitRepositoryRepository;
        this.sonarProjectRepository = sonarProjectRepository;
        //GitDAO
        //SonarDAO
    }

    public void execute(CreateProjectInput input, CreateProjectOutput output){//還沒完成，先下班晚點繼續
        Project project = new Project(input.getName(), input.getDescription(),
                "start time",
                input.getGitRepositoryID(),
                input.getSonarProjectID());
        projectRepository.createProject(project);
        
        output.setId(project.getId());
        output.setName(project.getName());
        output.setGitRepositories(project.getGitRepositories());
    }
//    public void execute(CreateProjectInput input, CreateProjectOutput output){
//        Project project = new Project(input.getName(), input.getDescription());
//        projectRepository.createProject(project);
//        output.setId(project.getId());
//        output.setName(project.getName());
//        output.setGitRepositories(project.getGitRepositories());
//    }
}
