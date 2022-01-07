package adapter.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class issuesServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private IssuesServlet issuesServlet;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        issuesServlet = new IssuesServlet();
        JSONObject requestJson = new JSONObject();
        requestJson.put("owner", "allen880612");
        requestJson.put("repo", "hello-world");

        request.addHeader("Content-Type","text/json");
        request.setContent(requestJson.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void GetIssueInfoTest() throws IOException {
        issuesServlet.doPost(request, response);
        JSONArray jsonArray = new JSONArray(response.getContentAsString());
        Assert.assertEquals("closed", jsonArray.getJSONObject(0).get("state"));
        Assert.assertEquals("Hello-World", jsonArray.getJSONObject(0).get("title"));
        Assert.assertEquals("First issue", jsonArray.getJSONObject(0).get("body"));
        Assert.assertEquals("2022-01-07T19:05:54Z", jsonArray.getJSONObject(0).get("created_at"));
        Assert.assertEquals("2022-01-07T19:07:16Z", jsonArray.getJSONObject(0).get("closed_at"));
    }
}
