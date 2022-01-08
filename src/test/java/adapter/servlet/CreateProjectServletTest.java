package adapter.servlet;

import adapter.project.ProjectRepositoryImpl;
import com.google.gson.Gson;
import dto.CreateProjectServletDTO;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import usecase.project.ProjectRepository;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class CreateProjectServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private CreateProjectServlet createProjectServlet;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        createProjectServlet = new CreateProjectServlet();

        CreateProjectServletDTO createProjectServletDto = new CreateProjectServletDTO();
        createProjectServletDto.setUserId("testUser");
        createProjectServletDto.setProjectName("createProjectName");
        createProjectServletDto.setProjectDescription("createProjectDescription");
        createProjectServletDto.setGithubUrl("https://github.com/allen880612/GitRepositoryAnalysisSystem");
        createProjectServletDto.setSonarHost("sonarHost");
        createProjectServletDto.setSonarProjectKey("sonarProjectKey");
        createProjectServletDto.setSonarToken("sonarToken");

        request.addHeader("Content-Type","text/json");
        request.setContent(new Gson().toJson(createProjectServletDto).getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void GetPersonalCommitsStatsTest() throws IOException, ServletException, SQLException {
        createProjectServlet.doPost(request, response);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        Assert.assertTrue(jsonObject.getBoolean("isSuccessful"));
        String projectId = jsonObject.getString("projectId");
        Assert.assertNotNull(projectId);
        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        projectRepository.deleteProject(projectId);
    }
}
