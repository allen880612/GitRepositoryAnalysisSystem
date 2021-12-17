package adapter.servlet;
import org.json.JSONObject;
import usecase.PostJSONWithHttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/authorizeGithub", name = "AuthorizeGithubServlet")
public class AuthorizeGithubServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        String code = String.valueOf(requestBody.get("code"));
        System.out.println("code: " + code);
        JSONObject jsonObject = new JSONObject();

        String post_url = "https://github.com/login/oauth/access_token";
        String client_id = System.getenv("Github_Client_Id");
        String client_secret = System.getenv("Github_Client_Secret");

        JSONObject body = new JSONObject();
        body.put("client_id", "27ea0af1aeabd39a7d5d");
        body.put("client_secret", "73982553f4ca36536646a033e1595dc126694b2e");
        body.put("code", code);

        PostJSONWithHttpURLConnection jp = new PostJSONWithHttpURLConnection();
        try {
            jsonObject = jp.makeHttpRequest(post_url, body.toString());
            System.out.println(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println(jsonObject) ;
        out.close();
    }
}
