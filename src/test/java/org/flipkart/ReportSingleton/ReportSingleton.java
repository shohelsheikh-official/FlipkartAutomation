package org.flipkart.ReportSingleton;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.sql.Timestamp;

public class ReportSingleton {

    static ExtentTest test;
    static ExtentReports report;

    public static ExtentTest getReportInstance() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timestampString = String.valueOf(timestamp.getTime());

        report = new ExtentReports(System.getProperty("user.dir")+"/FlipkartExtentReport"+timestampString+".html");
        report.loadConfig(new File(System.getProperty("user.dir")+"/src/extent_configs.xml"));

        // TODO - 2. Start a new test
        return test = report.startTest("Flipkart");

    }

    @AfterSuite
    public static void endReport() {
        if (report != null) {
            report.endTest(test);
            report.flush();
        }
    }
}