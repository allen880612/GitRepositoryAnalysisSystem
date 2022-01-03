package usecase.sonarproject;

public class CreateSonarProjectOutputImpl implements CreateSonarProjectOutput {
    private  boolean isSuccessful;
    private String sonarProjectId;

    @Override
    public void setIsSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    @Override
    public void setSonarProjectId(String sonarProjectId) {
        this.sonarProjectId = sonarProjectId;
    }

    @Override
    public boolean getIsSuccessful() {
        return this.isSuccessful;
    }

    @Override
    public String getSonarProjectId() {
        return this.sonarProjectId;
    }
}
