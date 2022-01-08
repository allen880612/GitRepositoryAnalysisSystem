package adapter.servlet;

import adapter.account.AccountRepositoryImpl;
import adapter.project.ProjectRepositoryImpl;
import com.google.gson.Gson;
import dto.CreateProjectServletDTO;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import usecase.account.AccountRepository;
import usecase.project.ProjectRepository;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DeleteProjectServletTest {
    private MockHttpServletRequest requestForDelete;
    private MockHttpServletResponse responseForDelete;

    private MockHttpServletRequest requestForCreate;
    private MockHttpServletResponse responseForCreate;

    private DeleteProjectServlet deleteProjectServlet;
    private CreateProjectServlet createProjectServlet;
    private String projectId;
    @Before
    public void setUp() throws ServletException, IOException {

        requestForDelete = new MockHttpServletRequest();
        responseForDelete = new MockHttpServletResponse();

        deleteProjectServlet = new DeleteProjectServlet();

        projectId = createAProjectAndReturnProjectId();

        JSONObject requestJson = new JSONObject();
        requestJson.put("userId","testUser");
        requestJson.put("projectId",projectId);

        requestForDelete.addHeader("Content-Type","text/json");
        requestForDelete.setContent(requestJson.toString().getBytes(StandardCharsets.UTF_8));
    }


    private String createAProjectAndReturnProjectId() throws ServletException, IOException {
        requestForCreate = new MockHttpServletRequest();
        responseForCreate = new MockHttpServletResponse();
        createProjectServlet = new CreateProjectServlet();
        CreateProjectServletDTO createProjectServletDto = new CreateProjectServletDTO();
        createProjectServletDto.setUserId("testUser");
        createProjectServletDto.setProjectName("createProjectName");
        createProjectServletDto.setProjectDescription("createProjectDescription");
        createProjectServletDto.setGithubUrl("https://github.com/allen880612/GitRepositoryAnalysisSystem");
        createProjectServletDto.setSonarHost("sonarHost");
        createProjectServletDto.setSonarProjectKey("sonarProjectKey");
        createProjectServletDto.setSonarToken("sonarToken");
        requestForCreate.addHeader("Content-Type","text/json");
        requestForCreate.setContent(new Gson().toJson(createProjectServletDto).getBytes(StandardCharsets.UTF_8));
        createProjectServlet.doPost(requestForCreate,responseForCreate);

        JSONObject jsonObject = new JSONObject(responseForCreate.getContentAsString());
        return  jsonObject.getString("projectId");
    }
    @Test
    public void deleteProjectTest() throws ServletException, IOException {
        deleteProjectServlet.doPost(requestForDelete,responseForDelete);
        JSONObject jsonObject = new JSONObject(responseForDelete.getContentAsString());
        AccountRepository accountRepository = new AccountRepositoryImpl();
        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        Boolean isSuccessful;
        if (projectRepository.getProjectWithoutRepositoryById(projectId) == null
                && !accountRepository.getAccountById("testUser").getProjects().contains(projectId)){
            isSuccessful = true;
        }        else{
            isSuccessful = false;
        }
        Assert.assertTrue(isSuccessful);
        Assert.assertEquals(isSuccessful.toString(),jsonObject.get("isSuccess"));
    }
}
