package adapter.servlet;

import adapter.SonarQubeAccessorImpl;
import adapter.project.ProjectRepositoryImpl;
import adapter.sonarproject.SonarProjectRepositoryImpl;
import com.google.gson.Gson;
import domain.Project;
import domain.SonarProject;
import dto.SonarQubeInfoDTO;
import org.json.JSONObject;
import usecase.SonarQubeAccessor;
import usecase.project.ProjectRepository;
import usecase.sonarproject.SonarProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getSonarInfo", name = "GetSonarInfoServlet")
public class GetSonarInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        String projectId = requestBody.getString("projectId");

        SonarProjectRepository sonarProjectRepository = new SonarProjectRepositoryImpl();
        SonarProject sonarProject = sonarProjectRepository.getSonarProjectByProjectId(projectId);

        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);
        SonarQubeInfoDTO sonarQubeInfoDto = sonarQubeAccessor.getSonarInfo();

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(sonarQubeInfoDto)) ;
        out.close();
    }
}
