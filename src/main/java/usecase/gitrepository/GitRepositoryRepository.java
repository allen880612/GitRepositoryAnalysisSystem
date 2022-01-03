package usecase.gitrepository;

import domain.GitRepository;

public interface GitRepositoryRepository {
    GitRepository getGitRepositoryByProjectId(String projectId);
    GitRepository getGitRepositoryById(String id);
    void createGitRepository(GitRepository gitRepository,String projectId);
    void deleteGitRepository(String id);
}
