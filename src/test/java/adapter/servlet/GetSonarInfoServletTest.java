package adapter.servlet;

import com.google.gson.Gson;
import dto.SonarIssueListDTO;
import dto.SonarQubeInfoDTO;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GetSonarInfoServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private GetSonarInfoServlet getSonarInfoServlet;
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        getSonarInfoServlet = new GetSonarInfoServlet();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectId","2831eaf7-1b48-4bd8-97fb-fb26bc4c1e11");
        request.addHeader("Content-Type","text/json");
        request.setContent(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
    }
    @Test
    public void getSonarInfoByProjectID() throws ServletException, IOException {
        getSonarInfoServlet.doPost(request,response);
        SonarQubeInfoDTO sonarQubeInfoDto  = new Gson().fromJson(response.getContentAsString(), SonarQubeInfoDTO.class);
        Assert.assertTrue(sonarQubeInfoDto.isSuccessful());
        Assert.assertEquals(24,sonarQubeInfoDto.getBugs());
        Assert.assertEquals(152,sonarQubeInfoDto.getCodeSmell());
        Assert.assertEquals(55.9,sonarQubeInfoDto.getCoverage(),0.001);
        Assert.assertEquals(4.6,sonarQubeInfoDto.getDuplication(),0.001);

    }
}
