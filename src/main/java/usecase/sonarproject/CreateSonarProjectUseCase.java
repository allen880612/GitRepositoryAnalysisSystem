package usecase.sonarproject;

import domain.GitRepository;
import domain.SonarProject;
import usecase.gitrepository.CreateGitRepositoryInput;
import usecase.gitrepository.CreateGitRepositoryOutput;
import usecase.gitrepository.GitRepositoryRepository;

public class CreateSonarProjectUseCase {
    private SonarProjectRepository sonarProjectRepository;

    public CreateSonarProjectUseCase(SonarProjectRepository sonarProjectRepository){
        this.sonarProjectRepository = sonarProjectRepository;
    }

    public void execute(CreateSonarProjectInput input, CreateSonarProjectOutput output) {
        SonarProject newSonarProject = new SonarProject(input.getHostUrl(),input.getProjectKey(),input.getToken());
        sonarProjectRepository.createSonarProject(newSonarProject,input.getProjectId());

        output.setIsSuccessful(true);//TODO 之後要改成以try catch來判斷是否為真
        output.setSonarProjectId(newSonarProject.getId());
    }
}
