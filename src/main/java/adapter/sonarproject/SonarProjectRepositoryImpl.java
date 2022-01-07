package adapter.sonarproject;

import database.Database;
import domain.GitRepository;
import domain.SonarProject;
import usecase.sonarproject.SonarProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SonarProjectRepositoryImpl implements SonarProjectRepository {

    private Connection conn;

    public SonarProjectRepositoryImpl() {
        conn = Database.getConnection();
    }

    @Override
    public SonarProject getSonarProjectBySonarProjectId(String sonarProjectId) {
        final String query = "SELECT sonar_project_id,host_url,token,project_key,project_id FROM sonarproject WHERE sonar_project_id=?";
        SonarProject sonarProject;
        try{
            assert conn!= null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, sonarProjectId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            sonarProject = new SonarProject(
                    sonarProjectId,
                    resultSet.getString("host_url"),
                    resultSet.getString("project_key"),
                    resultSet.getString("token")
            );
            return sonarProject;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SonarProject getSonarProjectByProjectId(String projectId) {
        final String query = "SELECT sonar_project_id,host_url,token,project_key FROM sonarproject WHERE project_id=?";
        SonarProject sonarProject;
        try{
            assert conn!= null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, projectId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            sonarProject = new SonarProject(
                    resultSet.getString("sonar_project_id"),
                    resultSet.getString("host_url"),
                    resultSet.getString("project_key"),
                    resultSet.getString("token")
            );
            return sonarProject;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public void createSonarProject(SonarProject sonarProject, String projectId) throws SQLException {
        final String insert = " INSERT INTO sonarproject(sonar_project_id, host_url, token, project_key, project_id) VALUES(?,?,?,?,?) ";
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insert);
            preparedStatement.setString (1,sonarProject.getId());
            preparedStatement.setString (2, sonarProject.getHostUrl());
            preparedStatement.setString (3, sonarProject.getToken());
            preparedStatement.setString (4, sonarProject.getProjectKey());
            preparedStatement.setString (5, projectId);
            preparedStatement.execute();
    }


    @Override
    public void deleteSonarProject(String sonarProjectId) {
        final String deleteFromSonarprojectTable = "DELETE FROM sonarproject WHERE sonar_project_id=?";

        try{
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(deleteFromSonarprojectTable);
            preparedStatement.setString(1, sonarProjectId);
            preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}

    }
}
