package tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.EmployeePage;
import pages.LoginPage;

public class EmployeeTest extends BaseTest {

    // 🔹 Positive DataProvider
    @DataProvider(name = "employeeData")
    public Object[][] getEmployeeData() {
        return new Object[][] {
            {"bro", "sis"},
            {"Jane", "Smith"},
            {"Mike", "Jordan"}
        };
    }

    // ✅ Positive test with multiple employees
    @Test(dataProvider = "employeeData")
    public void testAddEmployee(String firstName, String lastName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // unique first name to avoid duplicate IDs
        String uniqueFirstName = firstName + System.currentTimeMillis();

        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.addNewEmployee(uniqueFirstName, lastName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean personalDetails = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']"))).isDisplayed();
        Assert.assertTrue(personalDetails, "Employee was not added successfully!");

        employeePage.goToEmployeeList();
        employeePage.searchEmployeeByName(uniqueFirstName + " " + lastName);
        Assert.assertTrue(employeePage.isEmployeeInResults(uniqueFirstName, lastName),
                "Employee was not found in Employee List!");
    }

    // 🔹 Negative DataProvider
    @DataProvider(name = "invalidEmployeeData")
    public Object[][] getInvalidEmployeeData() {
        return new Object[][] {
            {"", "Doe"},        // ❌ Missing first name
            {"John", ""},       // ❌ Missing last name
            {"", ""}            // ❌ Both missing
        };
    }

    // ❌ Negative test with multiple invalid cases
    @Test(dataProvider = "invalidEmployeeData")
    public void testAddEmployeeWithInvalidData(String firstName, String lastName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.addNewEmployee(firstName, lastName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // expect the "Required" validation message
        boolean errorMessage = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[text()='Required']"))).isDisplayed();

        Assert.assertTrue(errorMessage, "Error message was not shown for invalid input!");
    }
}
