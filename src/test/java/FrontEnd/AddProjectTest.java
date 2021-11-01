package FrontEnd;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

        // Click button to add project page
        By addProjectLocator = By.xpath("//*[@id='add_project_btn']/*");
        scrollToElement(addProjectLocator);
        WebElement createProjectPageBtn = driver.findElement(addProjectLocator);
        createProjectPageBtn.click();
        ((JavascriptExecutor)driver).executeScript("scroll(0, 0);");

        String name = "test project";
        String description = "test project";
        String repoUrl = "https://github.com/qiurunze123/GeekQ-Tools";

        // Edit project info & create
        editProject(name, description, repoUrl);
        WebElement createProjectBtn = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        createProjectBtn.click();

        // Wait page transfer finished
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='myButton']")));

        // Valid project added present
        String expected = String.format("專案名稱:%s\n專案敘述:%s\nRepo個數:%d\n開始時間:", name, description, 1);
        List<WebElement> projectAddeds = driver.findElements(By.xpath("//button[@class='myButton']"));
        WebElement lastAddedProject = projectAddeds.get(projectAddeds.size() - 1);
        Assert.assertEquals(expected, lastAddedProject.getText());

        // [Teardown] delete project
        WebElement deleteButton = lastAddedProject.findElement(By.xpath("../button/img[@src='./assets/images/trash.png']/.."));
        deleteButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        int expectedCount = projectAddeds.size() - 1;
        projectAddeds.clear();
        projectAddeds = driver.findElements(By.xpath("//button[@class='myButton']"));
        Assert.assertEquals(expectedCount, projectAddeds.size());
    }

    @Test
    public void LoginSuccessTest(){
        login("test","test");
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[normalize-space()='Services']")));
        Assert.assertEquals(driver.getCurrentUrl(), Variables.HOMEPAGE_URL);
    }


    private void addRepo(String url){
        WebElement repoUrlInput = driver.findElement(By.xpath("//input[@name='FullNameofUser' and @placeholder='Input Repo url:']"));
        repoUrlInput.sendKeys(url);

        WebElement addRepoButton = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
        addRepoButton.click();
    }

    private void editProject(String name, String description, String repoUrl) {
        addRepo(repoUrl);
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = String.format("//h4[normalize-space()='%s[導入成功]']", repoUrl);

        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        WebElement projectNameInput = driver.findElement(By.xpath("//input[@name='FullNameofUser' and @placeholder='專案名稱']"));
        projectNameInput.sendKeys(name);

        WebElement describeInput = driver.findElement(By.xpath("//*[@id='ProjectDescriptionInputText']"));
        describeInput.sendKeys(description);
    }

}