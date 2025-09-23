package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static String takeScreenshot(WebDriver driver, String testName) {
	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String screenshotName = testName + "_" + timestamp + ".png";

	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String destDir = System.getProperty("user.dir") + "/screenshots/";
	    String destPath = destDir + screenshotName;

	    try {
	        // create folder if it does not exist
	        File directory = new File(destDir);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        FileUtils.copyFile(srcFile, new File(destPath));
	        System.out.println("📸 Screenshot saved at: " + destPath);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return destPath;
	}
}
