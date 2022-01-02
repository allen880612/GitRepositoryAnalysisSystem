package adapter.servlet;


import adapter.account.AccountRepositoryImpl;
import adapter.gitrepository.CreateGitRepositoryInputImpl;
import adapter.gitrepository.CreateGitRepositoryOutputImpl;
import adapter.gitrepository.GitRepositoryRepositoryImpl;
import adapter.project.CreateProjectInputImpl;
import adapter.project.CreateProjectOutputImpl;
import adapter.project.CreateProjectUseCase;
import adapter.project.ProjectRepositoryImpl;
import adapter.sonarproject.SonarProjectRepositoryImpl;
import domain.Account;
import domain.Project;
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
import usecase.sonarproject.SonarProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/createProject", name = "CreateProjectServlet")
public class CreateProjectServlet extends HttpServlet {
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
        String id = createProjectAndReturnId(userId, projectName, projectDescription);

        jsonObject.put("projectId", id);

        PrintWriter out = response.getWriter();
        out.println(jsonObject) ;
        out.close();
    }

    private String createProjectAndReturnId(String userId, String projectName, String projectDescription){
        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        GitRepositoryRepository gitRepositoryRepository = new GitRepositoryRepositoryImpl();
        SonarProjectRepository sonarProjectRepository = new SonarProjectRepositoryImpl();

        AccountRepository accountRepository = new AccountRepositoryImpl();
        CreateProjectInput input = new CreateProjectInputImpl();
        CreateProjectOutput output = new CreateProjectOutputImpl();
        input.setName(projectName);
        input.setDescription(projectDescription);

//        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectRepository);
        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectRepository,gitRepositoryRepository,sonarProjectRepository);

        createProjectUseCase.execute(input, output);
        String id = output.getId();

        CreateGitRepositoryUseCase createGitRepositoryUseCase = new CreateGitRepositoryUseCase(gitRepositoryRepository);
        CreateGitRepositoryInput createGitRepositoryInput = new CreateGitRepositoryInputImpl();
        CreateGitRepositoryOutput createGitRepositoryOutput = new CreateGitRepositoryOutputImpl();

        createGitRepositoryInput.setOwnerName("Peter Parker");
        createGitRepositoryInput.setRepoName("Spider man");
        createGitRepositoryUseCase.execute(createGitRepositoryInput,createGitRepositoryOutput);



        sonarProjectRepository.createSonarProject(new SonarProject("url123","projectkey123","token123"),id);







        Account account = accountRepository.getAccountById(userId);
        account.addProject(id);

        accountRepository.updateAccountOwnProject(account);
        return output.getId();
    }

}
