package FrontEnd;

import domain.Project;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddProjectTest extends FrontTestBase {

    @Test
    public void addRepoSuccessfullyTest(){
        login("test","test");
        goToAddProjectPage();

        String repoUrl = "https://github.com/qiurunze123/GeekQ-Tools";
        addRepo(repoUrl);

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = String.format("//h4[normalize-space()='%s[導入成功]']", repoUrl);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }
    @Test
    public void addRepoFailedTest(){
        login("test","test");
        goToAddProjectPage();

        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        addRepo(repoUrl);

        WebElement addRepoButton = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
        addRepoButton.click();

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h3[normalize-space()='此網址無效，請重新輸入']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }


    @Test
    public void addProjectSuccessfullyTest(){
        login("test","test");
        goToAddProjectPage();


        String repoUrl = "https://github.com/qiurunze123/GeekQ-Tools";
        addRepo(repoUrl);

        WebElement addRepoButton = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
        addRepoButton.click();

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = String.format("//h4[normalize-space()='%s[導入成功]']", repoUrl);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        WebElement projectNameInput = driver.findElement(By.xpath("//input[@name='FullNameofUser' and @placeholder='專案名稱']"));
        projectNameInput.sendKeys("test project");
        WebElement describeInput = driver.findElement(By.xpath("//*[@id='ProjectDescriptionInputText']"));
        describeInput.sendKeys("test project");
        WebElement createProjectBtn = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        createProjectBtn.click();
    }

    @Test
    public void LoginSuccessTest(){
        login("test","test");
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[normalize-space()='Services']")));
        Assert.assertEquals(driver.getCurrentUrl(), Variables.HOMEPAGE_URL);
    }


    public void addRepo(String url){
        WebElement repoUrlInput = driver.findElement(By.xpath("//input[@name='FullNameofUser' and @placeholder='Input Repo url:']"));
        repoUrlInput.sendKeys(url);


        WebElement addRepoButton = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
        addRepoButton.click();
    }
}