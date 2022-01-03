package usecase.sonarreposotiry;

import adapter.gitrepository.CreateGitRepositoryInputImpl;
import adapter.gitrepository.GitRepositoryRepositoryImpl;
import adapter.sonarproject.SonarProjectRepositoryImpl;
import domain.SonarProject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usecase.SonarQubeAccessor;
import usecase.gitrepository.CreateGitRepositoryInput;
import usecase.gitrepository.GitRepositoryRepository;
import usecase.sonarproject.SonarProjectRepository;

public class CreateSonarProjectRepositoryTest {
    private SonarProjectRepository sonarRepositoryRepository;


    @Before
    public void setUp() {

        this.sonarRepositoryRepository = new SonarProjectRepositoryImpl();
    }
    @Test
    public void Create_Sonar_Repository_Should_Commit_To_Repository_Test(){
        SonarProject sonarProject = new SonarProject("url123","projectkey123","token123");


        this.sonarRepositoryRepository.createSonarProject(sonarProject,"projectid123");
        SonarProject sonarProjectResult = this.sonarRepositoryRepository.getSonarProjectByProjectId("projectid123");
        Assert.assertEquals(sonarProjectResult.getId(), sonarProject.getId());
        Assert.assertEquals(sonarProjectResult.getProjectKey(), sonarProject.getProjectKey());
        Assert.assertEquals(sonarProjectResult.getHostUrl(), sonarProject.getHostUrl());
        Assert.assertEquals(sonarProjectResult.getToken(), sonarProject.getToken());

        SonarProject sonarProjectResult2 = this.sonarRepositoryRepository.getSonarProjectBySonarProjectId(sonarProject.getId());
        Assert.assertEquals(sonarProjectResult2.getId(), sonarProject.getId());
        Assert.assertEquals(sonarProjectResult2.getProjectKey(), sonarProject.getProjectKey());
        Assert.assertEquals(sonarProjectResult2.getHostUrl(), sonarProject.getHostUrl());
        Assert.assertEquals(sonarProjectResult2.getToken(), sonarProject.getToken());

        this.sonarRepositoryRepository.deleteSonarProject(sonarProject.getId());
    }


}
