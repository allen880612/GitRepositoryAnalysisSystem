package adapter.servlet;


import adapter.account.AccountRepositoryImpl;
import adapter.gitrepository.CreateGitRepositoryInputImpl;
import adapter.gitrepository.CreateGitRepositoryOutputImpl;
import adapter.gitrepository.GitRepositoryRepositoryImpl;
import adapter.project.CreateProjectInputImpl;
import adapter.project.CreateProjectOutputImpl;
import adapter.sonarproject.CreateSonarProjectInputImpl;
import com.google.gson.Gson;
import dto.CreateProjectServletDTO;
import org.json.JSONObject;
import usecase.project.CreateProjectUseCase;
import adapter.project.ProjectRepositoryImpl;
import adapter.sonarproject.SonarProjectRepositoryImpl;
import domain.Account;
import usecase.account.AccountRepository;
import usecase.gitrepository.CreateGitRepositoryInput;
import usecase.gitrepository.CreateGitRepositoryOutput;
import usecase.gitrepository.CreateGitRepositoryUseCase;
import usecase.gitrepository.GitRepositoryRepository;
import usecase.project.CreateProjectInput;
import usecase.project.CreateProjectOutput;
import usecase.project.ProjectRepository;
import usecase.sonarproject.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/createProject", name = "CreateProjectServlet")
public class CreateProjectServlet extends HttpServlet {

    class CreateProjectException extends Exception{
        CreateProjectException(String msg) { super(msg); }
        CreateProjectException() { super(); }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestBody = request.getReader().readLine();
        System.out.println(requestBody);

        JSONObject responseJson = new JSONObject();
        Gson gson = new Gson();
        String projectId;
        boolean isSuccessful;
        CreateProjectServletDTO requestDto = gson.fromJson(requestBody, CreateProjectServletDTO.class);

        try {
            projectId = createProjectAndReturnId(requestDto.getProjectName(), requestDto.getProjectDescription());
            addProjectOnUserID(requestDto.getUserId(), projectId);
            createGitRepository(requestDto.getGithubUrl(), projectId);
            createSonarProject(requestDto.getSonarHost(), requestDto.getSonarToken(), requestDto.getSonarProjectKey(), projectId);
            isSuccessful = true;
        } catch (CreateProjectException e) {
            projectId="";
            isSuccessful= false;
        }

        responseJson.put("projectId", projectId);
        responseJson.put("isSuccessful", isSuccessful);
        PrintWriter out = response.getWriter();
        out.println(responseJson);
        out.close();
    }

    private void createGitRepository(String githubUrl, String projectId) throws CreateProjectException  {
        GitRepositoryRepository gitRepositoryRepository = new GitRepositoryRepositoryImpl();
        CreateGitRepositoryInput input = new CreateGitRepositoryInputImpl();
        CreateGitRepositoryOutput output = new CreateGitRepositoryOutputImpl();
        CreateGitRepositoryUseCase createGitRepositoryUseCase = new CreateGitRepositoryUseCase(gitRepositoryRepository);

        input.setProjectID(projectId);
        input.setOwnerName(getRepoName(githubUrl, "github.com"));
        input.setRepoName(getRepoOwnerName(githubUrl, "github.com"));
        createGitRepositoryUseCase.execute(input, output);

        if (!output.getIsSuccessful()) throw new CreateProjectException("create git repository fail");
    }
    private String getRepoOwnerName(String validUrl, String keyWord) throws CreateProjectException {
        String[] metadatas = validUrl.split("/");
        for (int i = 0; i < metadatas.length; i++) {
            if (metadatas[i].equals(keyWord)) {
                return metadatas[i+2];
            }
        }
        throw new CreateProjectException("parse repository owner name fail!");
    }

    private String getRepoName(String validUrl, String keyWord) throws CreateProjectException {
        String[] metadatas = validUrl.split("/");
        for (int i = 0; i < metadatas.length; i++) {
            if (metadatas[i].equals(keyWord)) {
                return metadatas[i+1];
            }
        }
        throw new CreateProjectException("parse repository name fail!");
    }

    private String createProjectAndReturnId(String projectName, String projectDescription) throws CreateProjectException {

        ProjectRepository projectRepository = new ProjectRepositoryImpl();

        CreateProjectInput input = new CreateProjectInputImpl();
        CreateProjectOutput output = new CreateProjectOutputImpl();

        input.setName(projectName);
        input.setDescription(projectDescription);

        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectRepository);
        createProjectUseCase.execute(input, output);

        if(!output.getIsSuccessful()) throw new CreateProjectException("create project fail!");

        return output.getId();
    }


    private void createSonarProject(String sonarHost, String sonarToken, String sonarProjectKey, String projectId) throws CreateProjectException {

        SonarProjectRepository sonarProjectRepository = new SonarProjectRepositoryImpl();

        CreateSonarProjectInput input = new CreateSonarProjectInputImpl();
        CreateSonarProjectOutput output = new CreateSonarProjectOutputImpl();

        input.setHostUrl(sonarHost);
        input.setToken(sonarToken);
        input.setProjectKey(sonarProjectKey);
        input.setProjectId(projectId);

        CreateSonarProjectUseCase createSonarProjectUseCase = new CreateSonarProjectUseCase(sonarProjectRepository);
        createSonarProjectUseCase.execute(input,output);
        if (!output.getIsSuccessful()) throw new CreateProjectException();
    }

    private void addProjectOnUserID(String userId,String projectId) throws CreateProjectException{
        AccountRepository accountRepository = new AccountRepositoryImpl();
        Account account = accountRepository.getAccountById(userId);
        account.addProject(projectId);
        try {
            accountRepository.updateAccountOwnProject(account);
        } catch (SQLException e) {
            throw new CreateProjectException("add project to user fail.");
        }
    }
}
