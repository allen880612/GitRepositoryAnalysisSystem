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
        addGitRepo(repoUrl);

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = String.format("//h4[normalize-space()='%s[導入成功]']", repoUrl);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptyNameTest(){
        login("test","test");
        goToAddProjectPage();
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='Project Name不得為空']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptyGitUrlTest(){
        login("test","test");
        goToAddProjectPage();
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='GitHub RepoUrl不得為空']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectGitRepoFailedWithErrorUrlTest(){
        login("test","test");
        goToAddProjectPage();

        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        addGitRepo(repoUrl);

        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此git url無效，請重新輸入']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptySonarHostTest(){
        login("test","test");
        goToAddProjectPage();
        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='Sonar Input不得為空']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithErrorSonarHostTest(){
        login("test","test");
        goToAddProjectPage();
        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        addSonarHost(repoUrl);
        addSonarProjectKey(repoUrl);
        addSonarToken(repoUrl);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此sonar url無效，請重新輸入']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptySonarProjectKeyTest(){
        login("test","test");
        goToAddProjectPage();
        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='Sonar Input不得為空']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithErrorSonarProjectKeyTest(){
        login("test","test");
        goToAddProjectPage();
        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        addSonarHost(repoUrl);
        addSonarProjectKey(repoUrl);
        addSonarToken(repoUrl);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此sonar url無效，請重新輸入']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithErrorSonarTokenTest(){
        login("test","test");
        goToAddProjectPage();
        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        addSonarHost(repoUrl);
        addSonarProjectKey(repoUrl);
        addSonarToken(repoUrl);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此sonar url無效，請重新輸入']";
        //System.out.println(locator);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptySonarTokenTest(){
        login("test","test");
        goToAddProjectPage();
        String repoUrl = "https://qiurunze123/GeekQ-Tools";
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='Sonar Input不得為空']";
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



    private void addGitRepo(String url){
        WebElement repoUrlInput = driver.findElement(By.xpath("//input[@name='GitRepoUrl' and @placeholder='Input Repo url:']"));
        repoUrlInput.sendKeys(url);

//        WebElement addRepoButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
//        addRepoButton.click();
    }
    private void addSonarHost(String url){
        WebElement repoUrlInput = driver.findElement(By.xpath("//input[@name='SonarHost' and @placeholder='Sonar Host:']"));
        repoUrlInput.sendKeys(url);
    }
    private void addSonarProjectKey(String url){
        WebElement repoUrlInput = driver.findElement(By.xpath("//input[@name='SonarProjectKey' and @placeholder='Sonar Project Key:']"));
        repoUrlInput.sendKeys(url);
    }
    private void addSonarToken(String url){
        WebElement repoUrlInput = driver.findElement(By.xpath("//input[@name='SonarToken' and @placeholder='Sonar Token:']"));
        repoUrlInput.sendKeys(url);
    }


    private void editProject(String name, String description, String repoUrl) {
        addGitRepo(repoUrl);
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