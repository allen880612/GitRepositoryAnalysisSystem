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
    public void CreateProjectSuccessfullyTest(){
        login("test","test");
        WebDriverWait waiter_Loding = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter_Loding.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='add_button']")));
        WebElement addProjectBtn = driver.findElement(By.xpath("//button[@class='add_button']"));
        addProjectBtn.click();
        // Click button to add project page
//        By addProjectLocator = By.xpath("//*[@id='add_project_btn']/*");
//        scrollToElement(addProjectLocator);
//        WebElement createProjectPageBtn = driver.findElement(addProjectLocator);
//        createProjectPageBtn.click();
//        ((JavascriptExecutor)driver).executeScript("scroll(0, 0);");

        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://github.com/allen880612/GitRepositoryAnalysisSystem";
        String sonarhost="140.124.181.17:9000";
        String sonarkey="GSAS";
        String sonartoken="a53369b9a02a246677d37ba6bca10b233456b2b4";
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        // Edit project info & create
//        waiter_LodingCreateProject.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Create Project']")));
        WebElement createProjectBtn = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        createProjectBtn.click();
        // Wait page transfer finished
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='myButton']")));

        // Valid project added present
        String expected = "//*[@class='spanClass' and contains(text(), 'Createtest')]";
        List<WebElement> projectlist = driver.findElements(By.xpath("//*[@class='field_table']"));
        WebElement lastAddedProject = projectlist.get(projectlist.size() - 1);
//        System.out.print(projectlist.get(0));
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(expected)));

        // [Teardown] delete project
//        WebElement deleteButton = lastAddedProject.findElement(By.xpath("//button[@class='delete_button'"));
//        deleteButton.click();
//        WebDriverWait wait = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
//        wait.until(ExpectedConditions.alertIsPresent());
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
//
//        int expectedCount = projectAddeds.size() - 1;
//        projectAddeds.clear();
//        projectAddeds = driver.findElements(By.xpath("//button[@class='myButton']"));
//        Assert.assertEquals(expectedCount, projectAddeds.size());
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
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "";
        String sonarhost="140.124.181.17:9000";
        String sonarkey="GSAS";
        String sonartoken="a53369b9a02a246677d37ba6bca10b233456b2b4";
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='GitHub RepoUrl不得為空']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectGitRepoFailedWithErrorUrlTest(){
        login("test","test");
        goToAddProjectPage();
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://www.twitch.tv/godjj";
        String sonarhost="140.124.181.17:9000";
        String sonarkey="GSAS";
        String sonartoken="a53369b9a02a246677d37ba6bca10b233456b2b4";
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此git url無效，請重新輸入']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptySonarHostTest(){
        login("test","test");
        goToAddProjectPage();
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://github.com/allen880612/GitRepositoryAnalysisSystem";
        String sonarhost="";
        String sonarkey="GSAS";
        String sonartoken="a53369b9a02a246677d37ba6bca10b233456b2b4";
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='Sonar Input不得為空']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithErrorSonarHostTest(){
        login("test","test");
        goToAddProjectPage();
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://github.com/allen880612/GitRepositoryAnalysisSystem";
        String sonarhost="789";
        String sonarkey="GSAS";
        String sonartoken="a53369b9a02a246677d37ba6bca10b233456b2b4";
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此sonar url無效，請重新輸入']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptySonarProjectKeyTest(){
        login("test","test");
        goToAddProjectPage();
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://github.com/allen880612/GitRepositoryAnalysisSystem";
        String sonarhost="140.124.181.17:9000";
        String sonarkey="";
        String sonartoken="a53369b9a02a246677d37ba6bca10b233456b2b4";
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='Sonar Input不得為空']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithErrorSonarProjectKeyTest(){
        login("test","test");
        goToAddProjectPage();
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://github.com/allen880612/GitRepositoryAnalysisSystem";
        String sonarhost="140.124.181.17:9000";
        String sonarkey="789";
        String sonartoken="a53369b9a02a246677d37ba6bca10b233456b2b4";
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此sonar url無效，請重新輸入']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithEmptySonarTokenTest(){
        login("test","test");
        goToAddProjectPage();
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://github.com/allen880612/GitRepositoryAnalysisSystem";
        String sonarhost="140.124.181.17:9000";
        String sonarkey="GSAS";
        String sonartoken="";
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='Sonar Input不得為空']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    @Test
    public void CreateProjectFailedWithErrorSonarTokenTest(){
        login("test","test");
        goToAddProjectPage();
        String name = "Createtest";
        String description="create project for test";
        String repoUrl = "https://github.com/allen880612/GitRepositoryAnalysisSystem";
        String sonarhost="140.124.181.17:9000";
        String sonarkey="GSAS";
        String sonartoken="ERROR";
        InputProjectInfo(name,description,repoUrl,sonarhost,sonarkey,sonartoken);
        WebElement CreateProjectButton = driver.findElement(By.xpath("//button[normalize-space()='Create Project']"));
        CreateProjectButton.click();
        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        String locator = "//h5[normalize-space()='此sonar url無效，請重新輸入']";
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }




    private void addProjectName(String name){
        WebElement NameInput = driver.findElement(By.xpath("//input[@name='ProjectName' and @placeholder='專案名稱']"));
        NameInput.sendKeys(name);
    }
    private void addDescription(String description){
        WebElement DescriptionInput = driver.findElement(By.xpath("//textarea[@name='ProjectDescriptionInputTextArea' and @placeholder='長度限制:120字']"));
        DescriptionInput.sendKeys(description);
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

    private void InputProjectInfo(String name, String description, String repoUrl,String sonarhost,String sonarkey,String sonartoken) {
        addProjectName(name);
        addDescription(description);
        addGitRepo(repoUrl);
        addSonarHost(sonarhost);
        addSonarProjectKey(sonarkey);
        addSonarToken(sonartoken);
//        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
//        waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
//        String locator = String.format("//h4[normalize-space()='%s[導入成功]']", repoUrl);
//
//        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
//        Assert.assertEquals(driver.getCurrentUrl(), Variables.Choose_project_URL);
    }

}