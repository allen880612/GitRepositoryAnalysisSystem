package usecase.account;

public interface AuthorizeGithubInput {
    void setAccount(String account);
    String getAccount();
    void setPassword(String password);
    String getPassword();
    void setName(String name);
    String getName();
    void setToken(String token);
    String getToken();
}
