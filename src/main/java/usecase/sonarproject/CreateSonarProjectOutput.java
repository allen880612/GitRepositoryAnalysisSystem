package usecase.sonarproject;

public interface CreateSonarProjectOutput {

    void setIsSuccessful(boolean isSuccessful);

    boolean getIsSuccessful();

    void setSonarProjectId(String id);

    String getSonarProjectId();

}
