package usecase.gitrepository;

import domain.GitRepository;

import java.sql.SQLException;

public class CreateGitRepositoryUseCase {
    private GitRepositoryRepository gitRepositoryRepository;

    public CreateGitRepositoryUseCase(GitRepositoryRepository gitRepositoryRepository){
        this.gitRepositoryRepository = gitRepositoryRepository;
    }


    public void execute(CreateGitRepositoryInput input, CreateGitRepositoryOutput output) {
        GitRepository newGitRepository = new GitRepository(input.getRepoName(), input.getOwnerName());

        try {
            gitRepositoryRepository.createGitRepository(newGitRepository, input.getProjectID());
            output.setIsSuccessful(true);
        } catch (SQLException e) {
            e.printStackTrace();
            output.setIsSuccessful(false);
        }

        output.setResult(newGitRepository);
    }
}
