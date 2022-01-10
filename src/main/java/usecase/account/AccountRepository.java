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
    boolean verifyAccountWithGithubId(Account account);
    Account getAccountWithGithubId(Account account);
    void deleteAccount(String id);
    void deleteAccountRelations(String id);
    // TODO: check this should use where
    void deleteProjectRelations(String userId, String projectId) throws SQLException;
}
