package adapter.account;

import database.Database;
import domain.Account;
import domain.GitRepository;
import domain.Project;
import usecase.account.AccountRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private List<Account> accounts;
    private Connection conn;
    public  AccountRepositoryImpl(){
        accounts = new ArrayList<>();
        conn = Database.getConnection();
    }

    @Override
    public void createAccount(Account account) throws SQLException {
        accounts.add(account);
        final String insert = " INSERT INTO user(user_id, name, account, password, token) VALUES(?,?,?,?,?) ";
        assert conn != null;
        PreparedStatement preparedStatement = conn.prepareStatement(insert);
        preparedStatement.setString (1, account.getId());
        preparedStatement.setString (2, account.getName());
        preparedStatement.setString (3, account.getAccount());
        preparedStatement.setString (4, account.getPassword());
        preparedStatement.setString (5, account.getGithubToken());
        preparedStatement.execute();
    }

    @Override
    public Account getAccountById(String id) {
        final String query = " SELECT name, account, password FROM user WHERE user_id=?";
        Account account;
        try{
            assert conn!= null;
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            account = new Account(
                    id,
                    resultSet.getString("name"),
                    resultSet.getString("account"),
                    resultSet.getString("password")
            );
            for(String projectId : getAccountProjects(id)){
                account.addProject(projectId);
            }
            return account;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getAccountByAccountAndPassword(Account account) {
        final String query = " SELECT user_id, name, account, password FROM user WHERE account = ? AND password = ? ";
        Account queryAccount = null;
        try {
            PreparedStatement ps = null;
            ResultSet resultSet;
            this.conn = Database.getConnection();
            assert conn != null;
            ps = conn.prepareStatement(query);

            ps.setString(1,account.getAccount());
            ps.setString(2,account.getPassword());
            resultSet = ps.executeQuery();
            if(!resultSet.first()) return null;
            queryAccount = new Account(
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("account"),
                    resultSet.getString("password")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryAccount;
    }

    @Override
    public void updateAccountOwnProject(Account account) throws SQLException{
        if(!accounts.contains(account)) accounts.add(account);
        Account accountInDB = getAccountById(account.getId());
        accountInDB = accountInDB == null ? new Account("", "") : accountInDB;

        final String insert = " INSERT INTO user_project(user_id, project_id) VALUES(?,?) ";

        for(String projectId : account.getProjects()){
            if(accountInDB.getProjects().contains(projectId)) continue;
                assert conn != null;
                PreparedStatement preparedStatement = conn.prepareStatement(insert);
                preparedStatement.setString (1, account.getId());
                preparedStatement.setString (2, projectId);
                preparedStatement.execute();

        }

    }

    @Override
    public boolean verifyAccount(Account account) {
        final String query = " SELECT user_id,account, password FROM user WHERE account = ? AND password = ? ";
        Account queryAccount = null;
        try {
            PreparedStatement ps = null;
            ResultSet resultSet;
            assert conn != null;
            ps = conn.prepareStatement(query);

            ps.setString(1,account.getAccount());
            ps.setString(2,account.getPassword());
            resultSet = ps.executeQuery();
            resultSet.last();
            if(resultSet.getRow() == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean verifyAccountWithToken(Account account) {
        final String query = " SELECT user_id, name, account, token FROM user WHERE name = ? AND account = ? AND token = ? ";
        Account queryAccount = null;
        try {
            PreparedStatement ps = null;
            ResultSet resultSet;
            assert conn != null;
            ps = conn.prepareStatement(query);

            ps.setString(1,account.getName());
            ps.setString(2,account.getAccount());
            ps.setString(3,account.getGithubToken());
            resultSet = ps.executeQuery();
            resultSet.last();
            if(resultSet.getRow() == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getAccountWithToken(Account account) {
        final String query = " SELECT user_id, name, account, token FROM user WHERE name = ? AND account = ? AND token = ?";
        Account queryAccount = null;
        try {
            PreparedStatement ps = null;
            ResultSet resultSet;
            this.conn = Database.getConnection();
            assert conn != null;
            ps = conn.prepareStatement(query);

            ps.setString(1,account.getName());
            ps.setString(2,account.getAccount());
            ps.setString(3,account.getGithubToken());
            resultSet = ps.executeQuery();
            if(!resultSet.first()) return null;
            queryAccount = new Account(
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("account"),
                    ""
            );
            queryAccount.setGithubToken(resultSet.getString("token"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryAccount;
    }

    @Override
    public void deleteAccount(String id) {
        final String delete = "DELETE FROM user WHERE user_id=?";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void deleteAccountRelations(String id) {
        final String delete = "DELETE FROM user_project WHERE user_id=?";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
    }
    public boolean deleteProjectRelations(String userId, String projectId) {
        final String delete = "DELETE FROM user_project WHERE user_id=? AND project_Id=?";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(delete);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, projectId);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }

    private List<String> getAccountProjects(String id){
        final String query = " SELECT project_id FROM user_project WHERE user_id=? ";
        List<String> projects = new ArrayList<>();
        Account queryAccount = null;
        try{
            ResultSet resultSet;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.first()) return projects;
            do{
                projects.add(resultSet.getString("project_id"));
            }
            while(resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;

    }

}
