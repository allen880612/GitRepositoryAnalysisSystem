package adapter.servlet;
import adapter.GithubAccessorImpl;
import adapter.account.AccountRepositoryImpl;
import adapter.account.AuthorizeGithubInputImpl;
import adapter.account.AuthorizeGithubOutputImpl;
import com.google.gson.Gson;
import domain.Account;
import dto.GithubUserDTO;
import org.json.JSONObject;
import usecase.GithubAccessor;
import usecase.PostJSONWithHttpURLConnection;
import usecase.account.AccountRepository;
import usecase.account.AuthorizeGithubInput;
import usecase.account.AuthorizeGithubOutput;
import usecase.account.AuthorizeGithubUseCase;

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

        GithubAccessor githubAccessor = new GithubAccessorImpl();
        String token = githubAccessor.getGitHubToken(code);
        GithubUserDTO githubUserDto = githubAccessor.getUserInfo(token);

        AuthorizeGithubOutput authOutput = new AuthorizeGithubOutputImpl();
        authOutput.setAvatarUrl(githubUserDto.getAvatarUrl());

        if (githubUserDto.isSuccessful()) {
            AuthorizeGithubInput authInput = new AuthorizeGithubInputImpl();
            authInput.setAccount(githubUserDto.getAccount());
            authInput.setName(githubUserDto.getName());
            authInput.setGithubId(githubUserDto.getId());

            AuthorizeGithubUseCase authorizeGithubUseCase = new AuthorizeGithubUseCase(new AccountRepositoryImpl());
            authorizeGithubUseCase.execute(authInput, authOutput);
        } else {
            authOutput.setIsSuccessful(false);
        }

        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(authOutput));
        out.close();
    }
}
