package tests;

import helpers.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BankManagerPage;
import pages.BasePage;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;
    protected BankManagerPage bankManagerPage;
    private String URL = Property.getProperty("properties.basetest.baseurl");

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get(URL);
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        bankManagerPage = loginPage.loginAsManager();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
