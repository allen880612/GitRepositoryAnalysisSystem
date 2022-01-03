package adapter.servlet;

import adapter.account.AccountRepositoryImpl;
import adapter.gitrepository.GitRepositoryRepositoryImpl;
import adapter.project.ProjectRepositoryImpl;
import domain.GitRepository;
import domain.Project;
import org.json.JSONArray;
import org.json.JSONObject;
import usecase.account.AccountRepository;
import usecase.gitrepository.GitRepositoryRepository;
import usecase.project.ProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getProjectGitRepositories", name = "GetProjectGitRepositoriesServlet")
public class GetProjectGitRepositoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        String projectId = String.valueOf(requestBody.get("projectId"));

        GitRepositoryRepository gitRepositoryRepository = new GitRepositoryRepositoryImpl();

        JSONArray jsonArray = new JSONArray();

        GitRepository gitRepository = gitRepositoryRepository.getGitRepositoryByProjectId(projectId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ownerName", gitRepository.getOwnerName());
        jsonObject.put("repoName", gitRepository.getRepoName());
        jsonArray.put(jsonObject);

        PrintWriter out = response.getWriter();
        out.println(jsonArray);
        out.close();
    }
}
