package FrontEnd;
import org.openqa.selenium.By;

import org.junit.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest extends FrontTestBase {

    @Test
    public void LoginFailTest(){
        login("fail","fail");
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[normalize-space()='帳號或密碼錯誤']")));
    }

    @Test
    public void LoginSuccessTest(){
        login("test","test");
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='myButton']")));
        Assert.assertEquals(driver.getCurrentUrl(), Variables.Choose_project_URL);
    }
}