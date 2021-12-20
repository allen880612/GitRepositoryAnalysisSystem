package dto;

public class GithubUserDTO {
    private String name;
    private String account;
    private boolean isSuccessful;

    public GithubUserDTO(String name, String account) {
        this.name = name;
        this.account = account;
        this.isSuccessful = true;
    }

    public GithubUserDTO() {
        this.name = "";
        this.account = "";
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
}
