package FrontEnd;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
        Dimension dimension = new Dimension(1920, 1080);
        driver.manage().window().setSize(dimension);
        driver.manage().window().maximize();
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
    protected void goToAddProjectPage() {
        driver.get(Variables.ADD_PROJECT_URL);
        Assert.assertEquals(driver.getCurrentUrl(), Variables.ADD_PROJECT_URL);
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

        // Wait page loaded
//        WebDriverWait loginWaiter = new WebDriverWait(driver, Variables.TIME_OUT_SECONDS);
//        loginWaiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[normalize-space()='Services']")));
    }

    public void scrollToElement(By by){
        scrollToElement(by, 150);
    }

    public void scrollToElement(By by, int fixedY)
    {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        String js = String.format("scroll(0, %s);", fixedY);
        ((JavascriptExecutor)driver).executeScript("scroll(0, 150);");
    }
}
