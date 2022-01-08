package adapter.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginServletTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private LoginServlet loginServlet;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        loginServlet = new LoginServlet();
    }

    @Test
    public void loginWIthExistedAccountTest() throws ServletException, IOException {
        JSONObject requestJson = new JSONObject();
        requestJson.put("account","account");
        requestJson.put("password","password");

        request.addHeader("Content-Type","text/json");
        request.setContent(requestJson.toString().getBytes(StandardCharsets.UTF_8));
        loginServlet.doPost(request,response);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());

        Assert.assertEquals("true",((JSONArray)jsonObject.get("valid")).getString(0));
        Assert.assertEquals("fish han",((JSONArray)jsonObject.get("userName")).getString(0));
        Assert.assertEquals("testUser",((JSONArray)jsonObject.get("userId")).getString(0));
        Assert.assertEquals("homepage",((JSONArray)jsonObject.get("redirect")).getString(0));
    }
    @Test
    public void loginWIthNotExistedAccountTest() throws ServletException, IOException {
        JSONObject requestJson = new JSONObject();
        requestJson.put("account","PeterParker");
        requestJson.put("password","password");
        request.addHeader("Content-Type","text/json");
        request.setContent(requestJson.toString().getBytes(StandardCharsets.UTF_8));
        loginServlet.doPost(request,response);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        Assert.assertEquals("false",((JSONArray)jsonObject.get("valid")).getString(0));
    }
    @Test
    public void loginWIthExistedAccountAndWrongPasswordTest() throws ServletException, IOException {
        JSONObject requestJson = new JSONObject();
        requestJson.put("account","account");
        requestJson.put("password","hahahahahaha");
        request.addHeader("Content-Type","text/json");
        request.setContent(requestJson.toString().getBytes(StandardCharsets.UTF_8));
        loginServlet.doPost(request,response);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        Assert.assertEquals("false",((JSONArray)jsonObject.get("valid")).getString(0));
    }

}
