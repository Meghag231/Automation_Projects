package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("Admin", "admin123");

        String heading = dashboardPage.getDashboardHeading();
        Assert.assertEquals(heading, "Dashboard", "Login failed or Dashboard not displayed!");
    }

    @Test
    public void verifyInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "wrongPassword"); // this will stay on login page

        // Example: verify error message (implement getErrorMessage() in LoginPage if not already done)
        // String error = loginPage.getErrorMessage();
        // Assert.assertEquals(error, "Invalid credentials");
    }

    @Test
    public void verifyEmptyLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        // Example: verify error (implement getErrorMessage())
        // String error = loginPage.getErrorMessage();
        // Assert.assertEquals(error, "Username cannot be empty");
    }
}
