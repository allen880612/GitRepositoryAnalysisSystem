package adapter.servlet;

import org.json.JSONObject;
import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class AuthorizeGithubServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AuthorizeGithubServlet authorizeGithubServlet;
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        authorizeGithubServlet = new AuthorizeGithubServlet();
        JSONObject requestJson = new JSONObject();
        requestJson.put("code","asdifosdf");//這邊不知道放啥，先跳過，明天寫
    }
}
