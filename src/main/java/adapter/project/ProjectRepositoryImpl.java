package adapter.project;

import database.Database;
import domain.Project;
import usecase.project.ProjectRepository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    private List<Project> projects;
    private Connection conn;

    public ProjectRepositoryImpl(){
        projects = new ArrayList<>();
        conn = Database.getConnection();
    }

    public void createProject(Project project){
//        String id = "";
//        project.addGitRepository(id);
//        updateProject(project);
//        project.removeGitRepository(id);
        if(!projects.contains(project)) projects.add(project);

        final String insert = " INSERT INTO project(id, name, description) VALUES(?,?,?) ";

        assert conn != null;
        try{
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insert);
            preparedStatement.setString (1, project.getId());
            preparedStatement.setString (2, project.getName());
            preparedStatement.setString (3, project.getDescription());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProject(Project project){
        if(!projects.contains(project)) projects.add(project);
        Project projectInDB = getProjectById(project.getId());
        projectInDB = projectInDB == null ? new Project("empty", "empty") : projectInDB;

        final String insert = " INSERT INTO project(id, name, description, repoid) VALUES(?,?,?,?) ";
        for(String url : project.getGitRepositories()){
            if(projectInDB.getGitRepositories().contains(url)) continue;
            try{
                assert conn != null;
                PreparedStatement preparedStatement = conn.prepareStatement(insert);
                preparedStatement.setString (1, project.getId());
                preparedStatement.setString (2, project.getName());
                preparedStatement.setString (3, project.getDescription());
                preparedStatement.setString (4, url);
                preparedStatement.execute();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteProject(String id) {
        final String delete = "DELETE FROM project WHERE id=?";
        try{
            assert conn!= null;
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }

    public Project getProjectById(String id) {
        final String query = "SELECT name, repo.id as repoid, description, starttime " +
                             "FROM project " +
                             "LEFT JOIN gitrepository as repo " +
                             "on project.id = repo.projectid " +
                             "WHERE project.id =?";
        Project project;
        List<String> gitRepositories = new ArrayList<>();
        try{
            assert conn!= null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.first()) return null;
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String startTime = resultSet.getString("starttime");

            do{
                gitRepositories.add(resultSet.getString("repoid"));
            }
            while(resultSet.next());
            project = new Project(
                    id,
                    name,
                    description,
                    startTime,
                    gitRepositories
            );
            return project;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
