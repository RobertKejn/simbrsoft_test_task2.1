package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortCustomersTest extends BaseTest{
    @Test
    public void testCustomersSortedInDescendingOrder(){
        List<String> customersFromPage = bankManagerPage.getCustomersSortedInDescendingOrderOnPage();
        List<String> customersSorted = new ArrayList<>(customersFromPage)
                .stream()
                .sorted(Collections.reverseOrder())
                .toList();
        Assert.assertEquals(customersFromPage, customersSorted);
    }

    @Test
    public void testCustomersSortedInAscendingOrder(){
        List<String> customersFromPage = bankManagerPage.getCustomersSortedInAscendingOrderOnPage();
        List<String> customersSorted = new ArrayList<>(customersFromPage)
                .stream()
                .sorted()
                .toList();
        Assert.assertEquals(customersFromPage, customersSorted);
    }
}
