package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseTest {
    @Test
    @Description("Тест проверяет добавление нового клиента")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("UI")
    public void addCustomer() {
        bankManagerPage.addCustomer();

        String alertText = bankManagerPage.waitForMessageReceivedAlert();
        alertText = alertText.substring(0, alertText.indexOf(':') - 1);
        String expectedAlertText = "Customer added successfully with customer id";
        Assert.assertEquals(alertText, expectedAlertText);
    }
}
