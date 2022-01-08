package adapter.project;

import database.Database;
import domain.Project;
import usecase.project.ProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    private Connection conn;

    public ProjectRepositoryImpl() {

        conn = Database.getConnection();
    }

    public void createProject(Project project) throws SQLException {

        final String insert = " INSERT INTO project(project_id, name, description) VALUES(?,?,?) ";

        assert conn != null;
        PreparedStatement preparedStatement = conn.prepareStatement(insert);
        preparedStatement.setString(1, project.getId());
        preparedStatement.setString(2, project.getName());
        preparedStatement.setString(3, project.getDescription());
        preparedStatement.execute();

    }


    @Override
    public void deleteProject(String id) throws SQLException {
        final String delete = "DELETE FROM project WHERE project_id=?";

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

    }

    public Project getProjectWithoutRepositoryById(String id) {
        final String query = "SELECT project_id,name,description,starttime FROM PROJECT WHERE  project_id = ?";


        try {
            assert conn != null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.first()) return null;
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String startTime = resultSet.getString("starttime");


            Project project = new Project(
                    id,
                    name,
                    description,
                    startTime
            );

            return project;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Project getProjectWithRepositoryById(String id) {
        final String query = "SELECT PROJECT.project_id,PROJECT.name,PROJECT.description,PROJECT.starttime,SONAR.sonar_project_id,GITREPO.repo_id " +
                "FROM PROJECT , SONARPROJECT SONAR , GITREPOSITORY GITREPO " +
                "WHERE PROJECT.project_id = SONAR.project_id AND PROJECT.project_id = GITREPO.project_id  AND PROJECT.project_id = ?";


        try {
            assert conn != null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.first()) return null;
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String startTime = resultSet.getString("starttime");
            String gitRepoID = resultSet.getString("repo_id");
            String sonarProjectID = resultSet.getString("sonar_project_id");

            Project project = new Project(
                    id,
                    name,
                    description,
                    startTime,
                    gitRepoID,
                    sonarProjectID
            );

            return project;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
