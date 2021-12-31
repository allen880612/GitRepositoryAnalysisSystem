package adapter.servlet;

import adapter.SonarQubeAccessorImpl;
import adapter.project.ProjectRepositoryImpl;
import adapter.sonarproject.SonarProjectRepositoryImpl;
import com.google.gson.Gson;
import domain.Project;
import domain.SonarProject;
import dto.SonarBugListDTO;
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

@WebServlet(urlPatterns = "/getSonarBugList", name = "GetSonarBugListServlet")
public class GetSonarBugListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        String projectId = requestBody.getString("projectId");

        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        Project project = projectRepository.getProjectById(projectId);
        SonarProjectRepository sonarProjectRepository = new SonarProjectRepositoryImpl();
        // TODO: get sonarId from project
        // List<SonarProject> sonarProjectIds = project.getSonarProjects()
//        SonarProject sonarProject = sonarProjectRepository.getSonarProjectById("");
        String sonnarToken = "token";
        SonarProject sonarProject = new SonarProject("ip", "GSAS", sonnarToken);

        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);
        SonarBugListDTO sonarBugListDTO = sonarQubeAccessor.getSonarBugs();

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(sonarBugListDTO));
        out.close();
    }

}
