package usecase.account;


import domain.Account;

import java.sql.SQLException;

public class AuthorizeGithubUseCase {

    private AccountRepository accountRepository;
    public AuthorizeGithubUseCase(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }
    public void execute(AuthorizeGithubInput input, AuthorizeGithubOutput output) {
        Account authAccount = new Account(
                input.getName(),
                input.getAccount(),
                input.getPassword()
        );
        authAccount.setGithubToken(input.getToken());

        boolean isRegistered = accountRepository.verifyAccountWithToken(authAccount);
        if (isRegistered) {
            Account existAccount = accountRepository.getAccountWithToken(authAccount);
//            System.out.println(existAccount.getId());
            output.setId(existAccount.getId());
            output.setName(existAccount.getName());
        } else {
//            System.out.println("new Account");
            output.setId(authAccount.getId());
            output.setName(authAccount.getName());
            try {
                accountRepository.createAccount(authAccount);
            } catch (SQLException e) {
                output.setIsSuccessful(false);
            }
        }
    }
}
