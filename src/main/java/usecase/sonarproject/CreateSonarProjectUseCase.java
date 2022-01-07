package usecase.sonarproject;

import domain.GitRepository;
import domain.SonarProject;
import usecase.gitrepository.CreateGitRepositoryInput;
import usecase.gitrepository.CreateGitRepositoryOutput;
import usecase.gitrepository.GitRepositoryRepository;

import java.sql.SQLException;

public class CreateSonarProjectUseCase {
    private SonarProjectRepository sonarProjectRepository;

    public CreateSonarProjectUseCase(SonarProjectRepository sonarProjectRepository){
        this.sonarProjectRepository = sonarProjectRepository;
    }

    public void execute(CreateSonarProjectInput input, CreateSonarProjectOutput output) {
        SonarProject newSonarProject = new SonarProject(input.getHostUrl(),input.getProjectKey(),input.getToken());

        try {
            sonarProjectRepository.createSonarProject(newSonarProject,input.getProjectId());
            output.setIsSuccessful(true);
        } catch (SQLException e) {
            output.setIsSuccessful(false);
            e.printStackTrace();
        }

        output.setSonarProjectId(newSonarProject.getId());
    }
}
