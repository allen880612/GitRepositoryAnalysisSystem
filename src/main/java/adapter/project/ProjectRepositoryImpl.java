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

    public ProjectRepositoryImpl() {
        projects = new ArrayList<>();
        conn = Database.getConnection();
    }

    public void createProject(Project project) {
        String id = "";

        if (!projects.contains(project)) projects.add(project);
        Project projectInDB = getProjectById(project.getId());
        projectInDB = projectInDB == null ? new Project("empty", "empty") : projectInDB;

        final String insert = " INSERT INTO project(project_id, name, description, repoid,sonar_project_id) VALUES(?,?,?,?,?) ";


        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insert);
            preparedStatement.setString(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setString(4, project.getGitRepositoryID());
            preparedStatement.setString(5, project.getSonarProjectID());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    public void createProject(Project project){
//        String id = "";
//
//        if(!projects.contains(project)) projects.add(project);
//        Project projectInDB = getProjectById(project.getId());
//        projectInDB = projectInDB == null ? new Project("empty", "empty") : projectInDB;
//
//        final String insert = " INSERT INTO project(project_id, name, description, repoid,sonar_project_id) VALUES(?,?,?,?,?) ";
//        for(String url : project.getGitRepositories()){//如果
//            if(projectInDB.getGitRepositories().contains(url)) continue;
//            try{
//                assert conn != null;
//                PreparedStatement preparedStatement = conn.prepareStatement(insert);
//                preparedStatement.setString (1, project.getId());
//                preparedStatement.setString (2, project.getName());
//                preparedStatement.setString (3, project.getDescription());
//                preparedStatement.setString (4, url);
//                preparedStatement.setString (5, project.getSonarProjectID());
//                preparedStatement.execute();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//    public void createProject(Project project){
//        String id = "";
//        project.addGitRepository(id);
//        updateProject(project);
//        project.removeGitRepository(id);
//    }
    public void updateProject(Project project) {
        if (!projects.contains(project)) projects.add(project);
        Project projectInDB = getProjectById(project.getId());
        projectInDB = projectInDB == null ? new Project("empty", "empty") : projectInDB;

        final String update = "UPDATE project SET project_id = ?, name=?, description=?, repoid=?,sonar_project_id=? where project_id = ?";
        for (String url : project.getGitRepositories()) {
            if (projectInDB.getGitRepositories().contains(url)) continue;
            try {
                assert conn != null;
                PreparedStatement preparedStatement = conn.prepareStatement(update);
                preparedStatement.setString(1, project.getId());
                preparedStatement.setString(2, project.getName());
                preparedStatement.setString(3, project.getDescription());
                preparedStatement.setString(4, url);
                preparedStatement.setString(5, project.getSonarProjectID());
                preparedStatement.setString(6, project.getId());
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void updateProject(Project project){
//        if(!projects.contains(project)) projects.add(project);
//        Project projectInDB = getProjectById(project.getId());
//        projectInDB = projectInDB == null ? new Project("empty", "empty") : projectInDB;
//
//        final String insert = " INSERT INTO project(id, name, description, repoid) VALUES(?,?,?,?) ";
//        for(String url : project.getGitRepositories()){
//            if(projectInDB.getGitRepositories().contains(url)) continue;
//            try{
//                assert conn != null;
//                PreparedStatement preparedStatement = conn.prepareStatement(insert);
//                preparedStatement.setString (1, project.getId());
//                preparedStatement.setString (2, project.getName());
//                preparedStatement.setString (3, project.getDescription());
//                preparedStatement.setString (4, url);
//                preparedStatement.execute();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public boolean deleteProject(String id) {
        final String delete = "DELETE FROM project WHERE project_id=?";
        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Project getProjectById(String id) {
        final String query = "SELECT name, repoid,sonar_project_id, description, starttime FROM project WHERE projject_id=?";
        Project project;
        List<String> gitRepositories = new ArrayList<>();
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
            do {
                gitRepositories.add(resultSet.getString("repoid"));
            }
            while (resultSet.next());
            project = new Project(
                    id,
                    name,
                    description,
                    startTime,
                    gitRepositories
            );
            return project;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
