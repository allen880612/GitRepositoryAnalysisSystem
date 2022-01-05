package dto;

public class GithubUserDTO {
    private String name;
    private String account;
    private String id;
    private String avatarUrl;
    private boolean isSuccessful;

    public GithubUserDTO(String name, String account, String id, String avatarUrl) {
        this.name = name;
        this.account = account;
        this.id = id;
        this.avatarUrl = avatarUrl;

        this.isSuccessful = true;
    }

    public GithubUserDTO() {
        this.name = "";
        this.account = "";
        this.id = "";
        this.avatarUrl = "";
        this.isSuccessful = true;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}
