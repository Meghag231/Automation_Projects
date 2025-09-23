package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;


public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() 
    {
        // Create LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        DashboardPage dashboardPage = loginPage.login("Admin", "admin123");

        // Verify Dashboard is displayed
        String heading = dashboardPage.getDashboardHeading();
        Assert.assertEquals(heading, "Dashboard", "Login failed or Dashboard heading mismatch");
    }

    @Test
    public void verifyInvalidLogin() 
    {
        // Create LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Perform login with wrong password
        loginPage.login("Admin", "wrongPassword");

        // Verify error message
     }
    public void verifyemptyLogin()
    {
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.login("", "");
    	
    	}
    }
    