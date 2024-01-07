package org.flipkart.DriverSingleton;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.flipkart.PageElements.HomePageElements;
import org.flipkart.Pages.HomePage;
import org.flipkart.ReportSingleton.ReportSingleton;
import org.flipkart.context.PageContextUI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class DriverConfig {
    WebDriver driver;
    public PageContextUI pageContextUI;
    public ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void driverConfig(String browser){
        DriverSingleton.setDriver(browser);
        driver = DriverSingleton.getDriver();
        test = ReportSingleton.getReportInstance();
        pageContextUI = new PageContextUI(driver, test);
    }

    public static String capture(WebDriver driver, String filename) throws IOException {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(
                System.getProperty("user.dir") + "/src/FlipkartImages/"+ filename + System.currentTimeMillis() + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }

    @AfterMethod(alwaysRun = true)
    public void clearDriver(){
        DriverSingleton.getDriver().quit();
        DriverSingleton.clearThread();
    }
}
