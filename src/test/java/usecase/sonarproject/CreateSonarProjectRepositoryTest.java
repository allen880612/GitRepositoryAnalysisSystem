package usecase.sonarproject;

import adapter.sonarproject.CreateSonarProjectInputImpl;
import adapter.sonarproject.SonarProjectRepositoryImpl;
import domain.SonarProject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usecase.gitrepository.CreateGitRepositoryUseCase;

import java.sql.SQLException;

public class CreateSonarProjectRepositoryTest {
    private SonarProjectRepository sonarProjectRepository;


    @Before
    public void setUp() {

        this.sonarProjectRepository = new SonarProjectRepositoryImpl();
    }
    @Test
    public void Create_Sonar_Repository_Should_Commit_To_Repository_Test() throws SQLException {
        SonarProject sonarProject = new SonarProject("url123","projectkey123","token123");


        this.sonarProjectRepository.createSonarProject(sonarProject,"projectID");
        SonarProject sonarProjectResult = this.sonarProjectRepository.getSonarProjectByProjectId("projectID");
        Assert.assertEquals(sonarProjectResult.getId(), sonarProject.getId());
        Assert.assertEquals(sonarProjectResult.getProjectKey(), sonarProject.getProjectKey());
        Assert.assertEquals(sonarProjectResult.getHostUrl(), sonarProject.getHostUrl());
        Assert.assertEquals(sonarProjectResult.getToken(), sonarProject.getToken());

        SonarProject sonarProjectResult2 = this.sonarProjectRepository.getSonarProjectBySonarProjectId(sonarProject.getId());
        Assert.assertEquals(sonarProjectResult2.getId(), sonarProject.getId());
        Assert.assertEquals(sonarProjectResult2.getProjectKey(), sonarProject.getProjectKey());
        Assert.assertEquals(sonarProjectResult2.getHostUrl(), sonarProject.getHostUrl());
        Assert.assertEquals(sonarProjectResult2.getToken(), sonarProject.getToken());

        this.sonarProjectRepository.deleteSonarProject(sonarProject.getId());
    }

    @Test
    public void CreateSonarRepositoryUseCaseTest(){
        final String hostUrl = "url123";
        final String projectKey = "projectkey123";
        final String token = "token123";
        final String projectID = "projectID";

        CreateSonarProjectUseCase createSonarProjectUseCase = new CreateSonarProjectUseCase(this.sonarProjectRepository);
        CreateSonarProjectInput input = new CreateSonarProjectInputImpl();
        CreateSonarProjectOutput output = new CreateSonarProjectOutputImpl();

        input.setHostUrl(hostUrl);
        input.setProjectKey(projectKey);
        input.setToken(token);
        input.setProjectId(projectID);

        createSonarProjectUseCase.execute(input, output);

        String sonarProjectId = output.getSonarProjectId();

        SonarProject sonarProject = this.sonarProjectRepository.getSonarProjectBySonarProjectId(sonarProjectId);
        Assert.assertEquals(sonarProjectId, sonarProject.getId());
        Assert.assertEquals(projectKey, sonarProject.getProjectKey());
        Assert.assertEquals(hostUrl, sonarProject.getHostUrl());
        Assert.assertEquals(token, sonarProject.getToken());

        this.sonarProjectRepository.deleteSonarProject(sonarProject.getId());
    }

}
