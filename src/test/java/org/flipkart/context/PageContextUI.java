package org.flipkart.context;

import com.relevantcodes.extentreports.ExtentTest;
import org.flipkart.DriverSingleton.DriverSingleton;
import org.flipkart.Pages.HomePage;
import org.flipkart.ReportSingleton.ReportSingleton;
import org.openqa.selenium.WebDriver;

public class PageContextUI {
    WebDriver driver;
    HomePage homePage;
    public ExtentTest test;

    public PageContextUI(){}

    public PageContextUI(WebDriver driver, ExtentTest test){
        this.driver = driver;
        this.test = test;
        homePage = new HomePage(driver);
    }

    public HomePage getHomePage(){
        return homePage;
    }
}
