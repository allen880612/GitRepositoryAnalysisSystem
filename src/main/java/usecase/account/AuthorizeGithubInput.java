package usecase.account;

public interface AuthorizeGithubInput {
    void setAccount(String account);
    String getAccount();
    void setName(String name);
    String getName();
    void setGithubId(String githubId);
    String getGithubId();
}
