package org.flipkart.DriverSingleton;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.flipkart.PageElements.HomePageElements;
import org.flipkart.Pages.HomePage;
//import org.flipkart.ReportSingleton.ReportSingleton;
import org.flipkart.context.PageContextUI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DriverConfig {
    WebDriver driver;
    public PageContextUI pageContextUI;
    public ExtentTest test;
    public ExtentReports extent;
    public com.aventstack.extentreports.ExtentTest logger;
    public ExtentSparkReporter sparkReporter;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void driverConfig(String browser){
        DriverSingleton.setDriver(browser);
        driver = DriverSingleton.getDriver();
       // test = ReportSingleton.beforeTestMethod();
        pageContextUI = new PageContextUI(driver);
    }

    @BeforeTest
    public void beforeTestMethod() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateAndTime = dateFormat.format(new Date());
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "FlipkartExtentReport_" + currentDateAndTime + ".html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        logger = extent.createTest("Regression Test");
        sparkReporter.config().setDocumentTitle("Flipkart Automation Test Result");
        sparkReporter.config().setReportName("Regression Test Report for Flipkart");
        sparkReporter.config().setTheme(Theme.STANDARD);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log the test case failure
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Case Failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            // Log the test case success (if needed)
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case Passed", ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.SKIP) {
            // Log the test case skipped (if needed)
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case Skipped", ExtentColor.ORANGE));
        }
    }

    @AfterTest
    public void afterTestMethod() {

        extent.flush();

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
