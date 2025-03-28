package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BankManagerPage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeleteCustomerTest extends BaseTest {
    @Test
    public void deleteCustomerWithMiddleSizedName() {
        List<String> customersNames = bankManagerPage.getCustomersNames();
        int summ = 0;
        for (var customerName : customersNames) {
            summ += customerName.length();
        }
        int middle = (int) Math.round(summ / (double) customersNames.size());

        Set<String> customersNamesForDeletion = customersNames
                .stream()
                .filter(e -> e.length() == middle)
                .collect(Collectors.toSet());

        bankManagerPage.deleteCustomersWithNames(customersNamesForDeletion);

        List<String> namesAfterDeletion = bankManagerPage.getCustomersNames();
        List<String> checkedNamesAfterDeletion = customersNames
                .stream()
                .filter(e -> e.length() != middle)
                .toList();
        namesAfterDeletion = namesAfterDeletion.stream().sorted().toList();
        checkedNamesAfterDeletion = checkedNamesAfterDeletion.stream().sorted().toList();
        Assert.assertEquals(namesAfterDeletion, checkedNamesAfterDeletion);

    }
}
