package adapter.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GetProjectGitRepositoriesServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private GetProjectGitRepositoriesServlet projectGitRepositoriesServlet;
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        projectGitRepositoriesServlet = new GetProjectGitRepositoriesServlet();
        JSONObject requestJson = new JSONObject();
        requestJson.put("projectId","projectID");

        request.addHeader("Content-Type","text/json");
        request.setContent(requestJson.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void getGitRepositoryByProjectId() throws ServletException, IOException {
        projectGitRepositoriesServlet.doPost(request,response);
        JSONArray jsonArray = new JSONArray(response.getContentAsString());
        Assert.assertEquals("allen880612",jsonArray.getJSONObject(0).getString("ownerName"));
        Assert.assertEquals("GitRepositoryAnalysisSystem",jsonArray.getJSONObject(0).getString("repoName"));
    }
}
