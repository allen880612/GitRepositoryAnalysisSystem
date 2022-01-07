package usecase.gitrepository;

import domain.GitRepository;

import java.sql.SQLException;

public interface GitRepositoryRepository {
    GitRepository getGitRepositoryByProjectId(String projectId);
    GitRepository getGitRepositoryById(String id);
    void createGitRepository(GitRepository gitRepository,String projectId) throws SQLException;
    void deleteGitRepository(String id);
}
