package pages;


/*public class DashboardPage
{
	WebDriver driver;
	public DashboardPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	By dashboardHeading = By.xpath("//h6[text()='Dashboard']");
	public String getDashboardHeading()
	{
		return driver.findElement(dashboardHeading).getText();
		
	}
}*/


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for Dashboard heading
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to get the Dashboard heading text
    public String getDashboardHeading() {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(dashboardHeader))
                .getText();
    }
}

