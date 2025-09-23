package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest
{
	@Test
	public void verifyDashboardAfterLogin()
	{
		LoginPage loginPage=new LoginPage(driver);
		DashboardPage dashboardPage=loginPage.login("Admin", "admin123");
		
		String heading = dashboardPage.getDashboardHeading();
		
		Assert.assertEquals(heading,"Dashboard","Dashboard heading mismatch");
		
		
		
	}

}

