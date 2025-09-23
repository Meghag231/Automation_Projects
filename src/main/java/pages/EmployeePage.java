package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeLink = By.xpath("//a[text()='Add Employee']");
    private By empListLink = By.xpath("//a[text()='Employee List']");
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By searchBox = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By tableBody = By.cssSelector(".oxd-table-body");

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ✅ return EmployeePage after adding
    public EmployeePage addNewEmployee(String firstName, String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeLink)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);

        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

        return this; // chainable
    }

    // ✅ return EmployeePage after navigating
    public EmployeePage goToEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(empListLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Employee Information']")));
        return this;
    }

    // ✅ return EmployeePage after search
    public EmployeePage searchEmployeeByName(String fullName) {
        WebElement nameBox = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        nameBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        nameBox.sendKeys(Keys.DELETE);
        nameBox.sendKeys(fullName);
        nameBox.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableBody));

        return this;
    }

    // ✅ verify result (boolean)
    public boolean isEmployeeInResults(String firstname, String lastname) {
        String tableText = driver.findElement(tableBody).getText();
        return tableText.contains(firstname) && tableText.contains(lastname);
    }

    // ✅ return EmployeePage after opening record
    public EmployeePage openFirstEmployeeFromResults() {
        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-card')][1]")));
        new Actions(driver).doubleClick(row).perform();
        return this;
    }
}
