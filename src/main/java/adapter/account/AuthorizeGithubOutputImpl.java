package adapter.account;

import usecase.account.AuthorizeGithubOutput;

public class AuthorizeGithubOutputImpl implements AuthorizeGithubOutput {
    private boolean isSuccessful;
    private String id;
    private String name;
    private String avatarUrl;

    public AuthorizeGithubOutputImpl() { isSuccessful=true; }

    @Override
    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    @Override
    public void setIsSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    @Override
    public String getAvatarUrl() { return avatarUrl; }
}
