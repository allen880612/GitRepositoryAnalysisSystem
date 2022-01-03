package usecase.sonarreposotiry;

import adapter.sonarproject.SonarProjectRepositoryImpl;
import domain.SonarProject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usecase.sonarproject.SonarProjectRepository;

public class CreateSonarProjectRepositoryTest {
    private SonarProjectRepository sonarProjectRepository;


    @Before
    public void setUp() {

        this.sonarProjectRepository = new SonarProjectRepositoryImpl();
    }
    @Test
    public void Create_Sonar_Repository_Should_Commit_To_Repository_Test(){
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


}
