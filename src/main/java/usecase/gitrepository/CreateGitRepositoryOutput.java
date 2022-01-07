package usecase.gitrepository;

import domain.GitRepository;

public interface CreateGitRepositoryOutput {
    GitRepository getResult();
    void  setResult(GitRepository gitRepository);
    boolean getIsSuccessful();
    void setIsSuccessful(boolean isSuccessful);
}
