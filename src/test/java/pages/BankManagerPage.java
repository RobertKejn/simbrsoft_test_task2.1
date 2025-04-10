package pages;

import helpers.Property;
import helpers.Wait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BankManagerPage extends BasePage {
    public final int POST_CODE_LENGTH = Property.getPropertyInt("properties.bankmanagerpage.postcodelength");
    public final int LAST_NAME_LENGTH = Property.getPropertyInt("properties.bankmanagerpage.lastnamelength");
    @FindBy(xpath = "//div[contains(@class, 'center')]/button[contains(text(), 'Add Customer')]")
    WebElement addCustomerButtonSelection;

    @FindBy(xpath = "//button[contains(text(), 'Customers')]")
    WebElement customersButtonSelection;

    @FindBy(xpath = "//table/tbody")
    WebElement table;

    @FindBy(xpath = "//td/a[contains(text(), 'First Name')]")
    WebElement firstNameSortingLink;

    @FindBy(xpath = "//table/tbody/tr[1]")
    WebElement firstRowOfTable;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstNameInput;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    WebElement postCodeInput;

    @FindBy(xpath = "//form//button[contains(text(), 'Add Customer')]")
    WebElement addCustomerButton;

    public BankManagerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addCustomer() {
        Wait.waitUntillElementIsVisible(driver, addCustomerButtonSelection);
        addCustomerButtonSelection.click();

        Wait.waitUntillElementIsVisible(driver, firstNameInput);
        Wait.waitUntillElementIsVisible(driver, lastNameInput);
        Wait.waitUntillElementIsVisible(driver, postCodeInput);
        Wait.waitUntillElementIsVisible(driver, addCustomerButton);

        String firstName;
        String lastName;
        String postCode;

        Random r = new Random();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < POST_CODE_LENGTH; i++) {
            arr.add(r.nextInt(10));
        }
        firstName = getFirstName(arr);
        lastName = getLastName(r);
        postCode = getPostCode(arr);

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postCode);

        addCustomerButton.click();

    }

    public List<String> getCustomersNames() {
        Wait.waitUntillElementIsVisible(driver, customersButtonSelection);
        customersButtonSelection.click();
        Wait.waitUntillElementIsVisible(driver, table);

        List<WebElement> firstCellOfRows = table.findElements(By.xpath(".//tr/td[1]"));
        return firstCellOfRows.stream().map(e -> e.getText()).toList();
    }

    public void deleteCustomersWithNames(Set<String> customersNamesForDeletion) {
        Wait.waitUntillElementIsVisible(driver, customersButtonSelection);
        customersButtonSelection.click();
        Wait.waitUntillElementIsVisible(driver, table);
        firstNameSortingLink.click();
        Wait.waitUntillElementIsVisible(driver, firstRowOfTable);
        boolean found;
        do {
            found = false;
            List<WebElement> tableRows = table.findElements(By.xpath(".//tr"));
            for (var r : tableRows) {
                WebElement firstName = r.findElement(By.xpath("./td[1]"));
                WebElement deleteButton = r.findElement(By.xpath("./td/button"));
                if (customersNamesForDeletion.contains(firstName.getText())) {
                    deleteButton.click();
                    Wait.waitUntillElementIsVisible(driver, firstRowOfTable);
                    found = true;
                }
            }
        } while (found);
    }

    public List<String> getCustomersSortedInDescendingOrderOnPage() {
        Wait.waitUntillElementIsVisible(driver, customersButtonSelection);
        customersButtonSelection.click();
        Wait.waitUntillElementIsVisible(driver, table);
        firstNameSortingLink.click();
        Wait.waitUntillElementIsVisible(driver, firstRowOfTable);

        List<WebElement> firstCellOfRows = table.findElements(By.xpath(".//tr/td[1]"));
        return firstCellOfRows.stream().map(e -> e.getText()).toList();
    }

    public List<String> getCustomersSortedInAscendingOrderOnPage() {
        Wait.waitUntillElementIsVisible(driver, customersButtonSelection);
        customersButtonSelection.click();
        Wait.waitUntillElementIsVisible(driver, table);
        firstNameSortingLink.click();
        Wait.waitUntillElementIsVisible(driver, firstRowOfTable);
        firstNameSortingLink.click();
        Wait.waitUntillElementIsVisible(driver, firstRowOfTable);

        List<WebElement> firstCellOfRows = table.findElements(By.xpath(".//tr/td[1]"));
        return firstCellOfRows.stream().map(e -> e.getText()).toList();
    }

    public String waitForMessageReceivedAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        return alert.getText();
    }

    private String getPostCode(List<Integer> list) {
        String s = "";
        for (var e : list) s = s + e;
        return s;
    }

    private String getFirstName(List<Integer> list) {
        String s = "";
        for (int i = 0; i < list.size(); i += 2) {
            int charCode = (list.get(i) * 10 + list.get(i + 1)) % 26;
            s = s + (char) (charCode + 'a');
        }
        return s;
    }

    private String getLastName(Random r) {
        String s = "";
        for (int i = 0; i < LAST_NAME_LENGTH; i++) {
            s = s + (char) (r.nextInt(26) + 'a');
        }
        return s;
    }


}
