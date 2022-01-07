package adapter.servlet;

import adapter.SonarQubeAccessorImpl;
import org.json.JSONObject;
import usecase.SonarQubeAccessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/verifySonarUrl", name = "verifySonarUrlServlet")
public class VerifySonarUrlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(getClass().getName());

        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        String host = requestBody.getString("sonarHost");
        String projectKey = requestBody.getString("sonarProjectKey");
        String token = requestBody.getString("sonarToken");

        SonarQubeAccessor sonarAccessor = new SonarQubeAccessorImpl(host, projectKey, token);
        boolean isUrlValid = sonarAccessor.isSonarProjectValid();

        JSONObject returnJson = new JSONObject();
        returnJson.put("isUrlValid", isUrlValid);

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(returnJson);
        out.close();
    }
}
