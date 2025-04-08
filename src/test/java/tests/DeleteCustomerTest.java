package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeleteCustomerTest extends BaseTest {
    @Test
    @Description("Тест проверяет удаление клиентов банка")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("UI")
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
