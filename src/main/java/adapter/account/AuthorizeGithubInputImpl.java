package adapter.account;

import usecase.account.AuthorizeGithubInput;

public class AuthorizeGithubInputImpl implements AuthorizeGithubInput {
    private String account;
    private String password;
    private String name;
    private String token;

    public AuthorizeGithubInputImpl() {
        this.account = "";
        this.password = "";
        this.name = "";
        this.token = "";
    }

    public AuthorizeGithubInputImpl(String account, String password, String name, String token) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.token = token;
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
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
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
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
