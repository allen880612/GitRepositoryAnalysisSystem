package usecase.account;

import domain.Account;

public interface AuthorizeGithubOutput {
    void setIsSuccessful(boolean isSuccessful);
    boolean getIsSuccessful();
    void setId(String id);
    String getId();
    void setName(String name);
    String getName();
    void setAvatarUrl(String avatarUrl);
    String getAvatarUrl();
}
