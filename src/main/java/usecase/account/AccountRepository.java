package usecase.account;

import domain.Account;

import java.sql.Connection;
import java.sql.SQLException;

public interface AccountRepository {
    void createAccount(Account account) throws SQLException;
    Account getAccountById(String id);
    Account getAccountByAccountAndPassword(Account account);
    void updateAccountOwnProject(Account account) throws SQLException;
    boolean verifyAccount(Account account);
    boolean verifyAccountWithToken(Account account);
    Account getAccountWithToken(Account account);
    void deleteAccount(String id);
    void deleteAccountRelations(String id);
    void deleteProjectRelations(String userId, String projectId) throws SQLException;



}
