package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import pages.EmployeePage;

public class EmployeeTest extends BaseTest {

    // 🔹 Data-driven test (multiple employees)
    @DataProvider(name = "employeeData")
    public Object[][] getEmployeeData() {
        return new Object[][]{
            {"John", "Doe"},
            {"Jane", "Smith"},
            {"Mike", "Jordan"}
        };
    }
@Test(dataProvider = "employeeData")
public void testAddEmployee(String firstName, String lastName) {
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = loginPage.login("Admin", "admin123");

    Assert.assertNotNull(dashboardPage, "Login failed, cannot continue!");

    EmployeePage employeePage = dashboardPage.goToEmployeePage();
    boolean isAdded = employeePage
            .addNewEmployee(firstName, lastName)
            .goToEmployeeList()
            .searchEmployeeByName(firstName + " " + lastName)
            .isEmployeeInResults(firstName, lastName);

    Assert.assertTrue(isAdded, "Employee not found in list after adding!");
}


    // 🔹 Negative test (invalid employee data)
    @DataProvider(name = "invalidEmployeeData")
    public Object[][] getInvalidEmployeeData() {
        return new Object[][]{
            {"", "Doe"},    // Missing first name
            {"John", ""},   // Missing last name
            {"", ""}        // Missing both
        };
    }

  @Test(dataProvider = "invalidEmployeeData")
public void testAddEmployeeWithInvalidData(String firstName, String lastName) {
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = loginPage.login("Admin", "admin123");

    Assert.assertNotNull(dashboardPage, "Login failed, cannot continue!");

    EmployeePage employeePage = dashboardPage.goToEmployeePage();
    employeePage.addNewEmployee(firstName, lastName);

    // ✅ Example validation (you can extend EmployeePage to capture specific field error)
    // String error = employeePage.getValidationError();
    // Assert.assertEquals(error, "Required");
}

}


