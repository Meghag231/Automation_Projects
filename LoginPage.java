package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private By requiredMessage = By.xpath("//span[text()='Required']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Login method
    public DashboardPage login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new DashboardPage(driver);
    }

    // Error handling (optional for invalid login tests)
    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDashboardDisplayed() {
        try {
            return driver.findElement(dashboardHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAnyErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e1) {
            try {
                return driver.findElement(requiredMessage).isDisplayed();
            } catch (Exception e2) {
                return false;
            }
        }
    }
}
