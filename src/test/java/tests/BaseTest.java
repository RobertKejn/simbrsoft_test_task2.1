package tests;

import helpers.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BankManagerPage;
import pages.BasePage;
import pages.LoginPage;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;
    protected BankManagerPage bankManagerPage;
    private String URL = Property.getProperty("properties.basetest.baseurl");

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
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
