package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenshotUtil;

import java.time.Duration;

public class EmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeLink = By.xpath("//a[@class='oxd-topbar-body-nav-tab-item' and text()='Add Employee']");
    private By empListLink = By.xpath("//a[@class='oxd-topbar-body-nav-tab-item' and text()='Employee List']");
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By searchBox = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By tableBody = By.cssSelector(".oxd-table-body");

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // CI-friendly wait
    }

    public EmployeePage addNewEmployee(String firstName, String lastName) {
        // ✅ Step 1: Open PIM menu
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();

        // ✅ Step 2: Debug screenshot before clicking Add Employee
        ScreenshotUtil.takeScreenshot(driver, "before_click_add_employee");

        // ✅ Step 3: Click Add Employee tab
        wait.until(ExpectedConditions.visibilityOfElementLocated(addEmployeeLink));
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeLink)).click();

        // ✅ Step 4: Fill employee details
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return this;
    }

    public EmployeePage goToEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(empListLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Employee Information']")));
        return this;
    }

    public EmployeePage searchEmployeeByName(String fullName) {
        WebElement nameBox = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        nameBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        nameBox.sendKeys(Keys.DELETE);
        nameBox.sendKeys(fullName);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableBody));
        return this;
    }

    public boolean isEmployeeInResults(String firstname, String lastname) {
        String tableText = driver.findElement(tableBody).getText();
        return tableText.contains(firstname) && tableText.contains(lastname);
    }

    public EmployeePage openFirstEmployeeFromResults() {
        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-card')][1]")));
        new Actions(driver).doubleClick(row).perform();
        return this;
    }
}
