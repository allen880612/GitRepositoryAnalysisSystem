package FrontEnd;

import adapter.account.AccountRepositoryImpl;
import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import usecase.account.AccountRepository;

public class RegisterTest extends FrontTestBase {

    private AccountRepository accountRepository;
    @Before
    public void setUp(){
        accountRepository = new AccountRepositoryImpl();
    }

    @Test
    public void RegisterSuccessful(){
        register("register","register","register","register");

        WebDriverWait wait = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String textOnAlert = alert.getText();
        alert.accept();

        Assert.assertEquals("註冊成功!轉至登入頁面", textOnAlert);
        WebDriverWait WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[normalize-space()='Sign in to GRAS']")));
        Assert.assertEquals(Variables.REGISTERED_LOGIN_URL, driver.getCurrentUrl());

        // [Teardown]
        Account account = new Account("register", "register");
        account = accountRepository.getAccountByAccountAndPassword(account);
        accountRepository.deleteAccount(account.getId());
    }

    @Test
    public void RegisterWithDifferentPassword(){
        register("test","test","abc","abcd");

        WebDriverWait WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[normalize-space()='輸入密碼不一致']")));
    }

    @Test
    public void RegisterWithExistedUserNameShouldShowWarningMessage(){
        register("test","test","test","test");

        WebDriverWait WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[normalize-space()='此帳號已被使用，請重新命名']")));
    }

    @Test
    public void RegisterWithEmptyUserNameShouldShowWarningMessage(){
        register("test","","test","test");

        WebDriverWait WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        WarningWaiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[normalize-space()='帳號欄位不得為空']")));
    }
}