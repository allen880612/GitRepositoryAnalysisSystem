package adapter.account;

import usecase.account.AuthorizeGithubInput;

public class AuthorizeGithubInputImpl implements AuthorizeGithubInput {
    private String account;
    private String name;
    private String githubId;

    public AuthorizeGithubInputImpl() {
        this.account = "";
        this.name = "";
        this.githubId = "";
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getGithubId() {
        return githubId;
    }

    @Override
    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }
}
