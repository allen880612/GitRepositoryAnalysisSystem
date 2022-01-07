package adapter.gitrepository;

import database.Database;
import domain.GitRepository;
import usecase.gitrepository.GitRepositoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GitRepositoryRepositoryImpl implements GitRepositoryRepository {

    private Connection conn;

    public GitRepositoryRepositoryImpl() {
        conn = Database.getConnection();
    }

    @Override
    public GitRepository getGitRepositoryByProjectId(String projectId) {
        final String query = "SELECT repo_id,reponame, ownername FROM gitrepository WHERE project_id=?";
        GitRepository gitRepository;
        try {

            assert conn != null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, projectId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            gitRepository = new GitRepository(
                    resultSet.getString("repo_id"),
                    resultSet.getString("reponame"),
                    resultSet.getString("ownername")
            );
            return gitRepository;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GitRepository getGitRepositoryById(String id) {
        final String query = "SELECT reponame, ownername FROM gitrepository WHERE repo_id=?";
        GitRepository gitRepository;
        try {

            assert conn != null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            gitRepository = new GitRepository(
                    id,
                    resultSet.getString("reponame"),
                    resultSet.getString("ownername")
            );
            return gitRepository;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createGitRepository(GitRepository gitRepository, String projectId) throws SQLException {
        final String insert = " INSERT INTO gitrepository(repo_id, reponame, ownername, project_id) VALUES(?,?,?,?) ";
        assert conn != null;
        PreparedStatement preparedStatement = conn.prepareStatement(insert);
        preparedStatement.setString(1, gitRepository.getId());
        preparedStatement.setString(2, gitRepository.getRepoName());
        preparedStatement.setString(3, gitRepository.getOwnerName());
        preparedStatement.setString(4, projectId);
        preparedStatement.execute();
    }

    @Override
    public void deleteGitRepository(String gitRepoId) {
        final String delete = "DELETE FROM gitrepository WHERE repo_id=?";
        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, gitRepoId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
