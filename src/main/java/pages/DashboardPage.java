package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By dashboardHeading = By.xpath("//h6[text()='Dashboard']");
    private By pimMenu = By.xpath("//span[text()='PIM']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public String getDashboardHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeading)).getText();
    }

    public EmployeePage goToEmployeePage() {
        // ✅ Ensure PIM menu is visible first
        wait.until(ExpectedConditions.presenceOfElementLocated(pimMenu));
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
        return new EmployeePage(driver);
    }
}
