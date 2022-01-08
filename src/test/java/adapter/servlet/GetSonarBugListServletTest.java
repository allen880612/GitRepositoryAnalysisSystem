package adapter.servlet;

import com.google.gson.Gson;
import dto.SonarIssueListDTO;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GetSonarBugListServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private GetSonarBugListServlet getSonarBugListServlet;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        getSonarBugListServlet = new GetSonarBugListServlet();
        JSONObject requestJson = new JSONObject();
        requestJson.put("projectId","2831eaf7-1b48-4bd8-97fb-fb26bc4c1e11");

        request.addHeader("Content-Type","text/json");
        request.setContent(requestJson.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void getSonarBugListByProjectId() throws ServletException, IOException {
        getSonarBugListServlet.doPost(request,response);
        SonarIssueListDTO sonarIssueListDTO = new Gson().fromJson(response.getContentAsString(), SonarIssueListDTO.class);

        Assert.assertTrue(sonarIssueListDTO.isSuccessful());
//        Assert.assertEquals(615,sonarIssueListDTO.getCount());
//        Assert.assertEquals(4502,sonarIssueListDTO.getEffortTotal(),0.001);

    }
}
