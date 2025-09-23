package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ScreenshotUtil;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setupBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(2000); // small wait for page load
    }

    @AfterMethod
//   public void teardownBrowser() {
//       if (driver != null) {
//           driver.quit();
//           }
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
        driver.quit();
    }
    
   /* @AfterMethod
    public void tearDown(ITestResult result) {
        // always capture screenshot
        ScreenshotUtil.takeScreenshot(driver, result.getName());
        driver.quit();
    }*/

}

