package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class SortCustomersTest extends BaseTest{
    @Test
    @Description("Тест проверяет сортировку клиенто в по именив порядке убывания")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("UI")
    public void testCustomersSortedInDescendingOrder(){
        List<String> customersFromPage = bankManagerPage.getCustomersSortedInDescendingOrderOnPage();
        List<String> customersSorted = new ArrayList<>(customersFromPage)
                .stream()
                .sorted(Collections.reverseOrder())
                .toList();
        Assert.assertEquals(customersFromPage, customersSorted);
    }

    @Test
    @Description("Тест проверяет сортировку клиентов по имени в порядке возростания")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("UI")
    public void testCustomersSortedInAscendingOrder(){
        List<String> customersFromPage = bankManagerPage.getCustomersSortedInAscendingOrderOnPage();
        List<String> customersSorted = new ArrayList<>(customersFromPage)
                .stream()
                .sorted()
                .toList();
        Assert.assertEquals(customersFromPage, customersSorted);
    }
}
