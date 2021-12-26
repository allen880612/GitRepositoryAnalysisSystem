package adapter.sonarproject;

import database.Database;
import domain.GitRepository;
import domain.SonarProject;
import usecase.sonarproject.SonarProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SonarProjectRepositoryImpl implements SonarProjectRepository {
    private List<SonarProject> sonarProjects;
    private Connection conn;

    public SonarProjectRepositoryImpl() {
        this.sonarProjects = new ArrayList<>();
        conn = Database.getConnection();
    }

    @Override
    public SonarProject getSonarProjectById(String id) {
        final String query = "SELECT sonar_project_id,host_url,token,project_key,project_id FROM sonarproject WHERE sonar_project_id=?";
        SonarProject sonarProject;
        try{
            assert conn!= null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            sonarProject = new SonarProject(
                    id,
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
    public void createSonarProject(SonarProject sonarProject) {
        sonarProjects.add(sonarProject);
        final String insert = " INSERT INTO sonarproject(sonar_project_id, host_url, token, project_key, project_id) VALUES(?,?,?,?,?) ";
//        final String queryProjectIdBySonarProject = "SELECT project_id FROM project WHERE sonar_project_id=?";
        //現在project TABLE尚未有sonar_project_id這個欄位，可能要再討論看怎麼用甚麼方式，來得到sonarProject的project_id
        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insert);
            preparedStatement.setString (1,sonarProject.getId());
            preparedStatement.setString (2, sonarProject.getHostUrl());
            preparedStatement.setString (3, sonarProject.getToken());
            preparedStatement.setString (4, sonarProject.getProjectKey());
//            preparedStatement.setString (5, );
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void deleteSonarProject(String id) {
        final String deleteFromSonarprojectTable = "DELETE FROM sonarproject WHERE sonar_project_id=?";
//        final String deleteFromProjectTable = "DELETE FROM project WHERE sonar_project_id=?";

        try{
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(deleteFromSonarprojectTable);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}

//        try{
//            assert conn != null;
//            PreparedStatement preparedStatement = conn.prepareStatement(deleteFromProjectTable);
//            preparedStatement.setString(1, id);
//            preparedStatement.executeUpdate();
//        }catch (Exception e){e.printStackTrace();}
    }
}
