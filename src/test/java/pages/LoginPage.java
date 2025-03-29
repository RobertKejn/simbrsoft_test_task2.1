package pages;

import helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//button[contains(text(), 'Bank Manager Login')]")
    WebElement bankManagerLoginButton;
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public final BankManagerPage loginAsManager(){
        bankManagerLoginButton.click();
        return new BankManagerPage(driver);
    }
}
