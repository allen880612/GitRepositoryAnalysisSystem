package adapter.servlet;


import adapter.account.AccountRepositoryImpl;
import adapter.account.CreateAccountInputImpl;
import adapter.gitrepository.CreateGitRepositoryInputImpl;
import adapter.gitrepository.CreateGitRepositoryOutputImpl;
import adapter.gitrepository.GitRepositoryRepositoryImpl;
import adapter.project.CreateProjectInputImpl;
import adapter.project.CreateProjectOutputImpl;
import adapter.sonarproject.CreateSonarProjectInputImpl;
import domain.Project;
import usecase.account.CreateAccountInput;
import usecase.account.CreateAccountUseCase;
import usecase.project.CreateProjectUseCase;
import adapter.project.ProjectRepositoryImpl;
import adapter.sonarproject.SonarProjectRepositoryImpl;
import domain.Account;
import domain.SonarProject;
import org.json.JSONObject;
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

    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        JSONObject requestBody = new JSONObject(request.getReader().readLine());
        System.out.println(requestBody);
        String userId = String.valueOf(requestBody.get("userId"));
        String projectName = String.valueOf(requestBody.get("projectName"));
        String projectDescription = String.valueOf(requestBody.get("projectDescription"));

        String githubUrl = String.valueOf(requestBody.get("githubUrl"));
        String sonarHost = requestBody.getString("sonarHost");
        String sonarToken = requestBody.getString("token");
        String sonarProjectKey = requestBody.getString("sonarProjectKey");

        String projectId;
        boolean isSuccessful;
        try {
            projectId = createProjectAndReturnId(userId, projectName, projectDescription, githubUrl, sonarHost, sonarToken, sonarProjectKey);
            isSuccessful = true;
        }catch (CreateProjectException e){
            projectId="";
            isSuccessful= false;
        }
        jsonObject.put("projectId", projectId);
        jsonObject.put("isSuccessful",isSuccessful);
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.close();
    }
    private String createProjectAndReturnId(String userId, String projectName, String projectDescription, String githubUrl, String sonarHost, String sonarToken, String sonarProjectKey) throws CreateProjectException {
        String projectId = useProjectUseCaseAndReturnProjectID(projectName, projectDescription);
        addProjectOnUserID(userId, projectId);
        useGitRepositoryUseCase(githubUrl,projectId);
        useSonarProjectUseCase(sonarHost,sonarToken,sonarProjectKey,projectId);
        return projectId;
    }

    private void useGitRepositoryUseCase(String githubUrl,String projectId)throws CreateProjectException  {
        GitRepositoryRepository gitRepositoryRepository = new GitRepositoryRepositoryImpl();
        CreateGitRepositoryInput input = new CreateGitRepositoryInputImpl();
        CreateGitRepositoryOutput output = new CreateGitRepositoryOutputImpl();
        CreateGitRepositoryUseCase createGitRepositoryUseCase = new CreateGitRepositoryUseCase(gitRepositoryRepository);
        input.setProjectID(projectId);
        input.setOwnerName(getRepoName(githubUrl, "github.com"));
        input.setRepoName(getRepoOwnerName(githubUrl, "github.com"));
        createGitRepositoryUseCase.execute(input, output);
        if(!output.getIsSuccessful())throw new CreateProjectException();

    }
    private String getRepoOwnerName(String validUrl, String keyWord){
        String[] metadatas = validUrl.split("/");
        for (int i = 0; i < metadatas.length; i++) {
            if (metadatas[i].equals(keyWord)) {
                return metadatas[i+2];
            }
        }
        return null;
    }

    private String getRepoName(String validUrl, String keyWord){
        String[] metadatas = validUrl.split("/");
        for (int i = 0; i < metadatas.length; i++) {
            if (metadatas[i].equals(keyWord)) {
                return metadatas[i+1];
            }
        }
        return null;
    }

    private String useProjectUseCaseAndReturnProjectID(String projectName, String projectDescription)throws CreateProjectException {

        ProjectRepository projectRepository = new ProjectRepositoryImpl();

        CreateProjectInput input = new CreateProjectInputImpl();
        CreateProjectOutput output = new CreateProjectOutputImpl();

        input.setName(projectName);
        input.setDescription(projectDescription);

        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectRepository);
        createProjectUseCase.execute(input, output);

        if(!output.getIsSuccessful())throw new CreateProjectException();
        String projectId = output.getId();

        return projectId;
    }


    private void useSonarProjectUseCase(String sonarHost, String sonarToken, String sonarProjectKey,String projectId) throws CreateProjectException {

        SonarProjectRepository sonarProjectRepository = new SonarProjectRepositoryImpl();

        CreateSonarProjectInput input = new CreateSonarProjectInputImpl();
        CreateSonarProjectOutput output = new CreateSonarProjectOutputImpl();

        input.setHostUrl(sonarHost);
        input.setToken(sonarToken);
        input.setProjectKey(sonarProjectKey);
        input.setProjectId(projectId);

        CreateSonarProjectUseCase createSonarProjectUseCase = new CreateSonarProjectUseCase(sonarProjectRepository);
        createSonarProjectUseCase.execute(input,output);
        if(!output.getIsSuccessful())throw new CreateProjectException();
    }

    private void addProjectOnUserID(String userId,String projectId) throws CreateProjectException{
        AccountRepository accountRepository = new AccountRepositoryImpl();
        Account account = accountRepository.getAccountById(userId);
        account.addProject(projectId);

        try {
            accountRepository.updateAccountOwnProject(account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CreateProjectException();
        }
    }
}
