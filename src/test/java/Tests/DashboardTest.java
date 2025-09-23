package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;

public class DashboardTest extends BaseTest {

    @Test
    public void verifyDashboardAfterLogin() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("Admin", "admin123");

        String heading = dashboardPage.getDashboardHeading();
        Assert.assertEquals(heading, "Dashboard", "Dashboard heading mismatch!");
    }
}
