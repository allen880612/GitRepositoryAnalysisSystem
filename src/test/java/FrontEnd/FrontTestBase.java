package FrontEnd;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontTestBase {
    protected WebDriver driver;

    @BeforeClass
    public static void setupWebdriverChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Variables.DRIVER_PATH);
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void goToLoginPage() {
        driver.get(Variables.LOGIN_URL);
        Assert.assertEquals(driver.getCurrentUrl(), Variables.LOGIN_URL);
    }
    protected void goToRegisterPage() {
        driver.get(Variables.REGISTER_URL);
        Assert.assertEquals(driver.getCurrentUrl(), Variables.REGISTER_URL);
    }

    protected void register(String fullName,String account,String password,String typePasswordAgain){
        goToRegisterPage();
        WebElement fullNameInput = driver.findElement(By.xpath("//*[@name='FullNameofUser']"));
        WebElement accountInput = driver.findElement(By.xpath("//input[@name='Account']"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@name='Password']"));
        WebElement typePasswordAgainInput = driver.findElement(By.xpath("//*[@name='TypePasswordAgain']"));
        By signUpButtonLocator = By.xpath("//button[normalize-space()='註冊']");
        WebElement signUpButton = driver.findElement(signUpButtonLocator);

        fullNameInput.sendKeys(fullName);
        Assert.assertEquals(fullName, fullNameInput.getAttribute("value"));

        accountInput.sendKeys(account);
        Assert.assertEquals(account, accountInput.getAttribute("value"));

        passwordInput.sendKeys(password);
        Assert.assertEquals(password, passwordInput.getAttribute("value"));

        typePasswordAgainInput.sendKeys(typePasswordAgain);
        Assert.assertEquals(typePasswordAgain, typePasswordAgainInput.getAttribute("value"));

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.elementToBeClickable(signUpButtonLocator));
        signUpButton.click();
    }

    protected  void login(String account, String password)
    {
    goToLoginPage();
        WebElement accountInput = driver.findElement(By.xpath("//input[@name='account']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        By loginButtonLocator = By.xpath("//button[normalize-space()='登入']");
        WebElement loginButton = driver.findElement(loginButtonLocator);

        accountInput.sendKeys(account);
        Assert.assertEquals(account, accountInput.getAttribute("value"));
        passwordInput.sendKeys(password);

        WebDriverWait waiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
        waiter.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }

    public boolean isAlertPresent() {

        boolean presentFlag = false;

        try {
            // Check the presence of alert
            driver.switchTo().alert();
            // Alert present; set the flag
            presentFlag = true;
            // if present consume the alert
//            alert.accept();

        } catch (NoAlertPresentException ex) {
            // Alert not present
//            ex.printStackTrace();
            presentFlag = false;
        }

        return presentFlag;

    }
}
