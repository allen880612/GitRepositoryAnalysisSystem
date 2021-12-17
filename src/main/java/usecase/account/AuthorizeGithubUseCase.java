package usecase.account;


import domain.Account;

import java.sql.SQLException;

public class AuthorizeGithubUseCase {

    private AccountRepository accountRepository;
    public AuthorizeGithubUseCase(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }
    public void execute(AuthorizeGithubInput input, AuthorizeGithubOutput output) {

        Account admin = new Account(
                input.getName(),
                input.getAccount(),
                input.getPassword()
        );
        admin.setGithubToken(input.getToken());

        output.setId(admin.getId());
        output.setName(admin.getName());
        output.setIsSuccessful(true);

        try {
            accountRepository.createAccount(admin);
        } catch (SQLException e) {
            output.setIsSuccessful(false);
        }
    }
}
