package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseTest{
    @Test
    public void addCustomer() throws InterruptedException {
        bankManagerPage.addCustomer();

        String alertText = bankManagerPage.waitForMessageReceivedAlert();
        alertText = alertText.substring(0, alertText.indexOf(':')-1);
        String expectedAlertText = "Customer added successfully with customer id";
        Assert.assertEquals(alertText, expectedAlertText);
    }
}
