package org.flipkart.tests;

import com.relevantcodes.extentreports.LogStatus;
import org.flipkart.DriverSingleton.DriverConfig;
import org.flipkart.DriverSingleton.DriverSingleton;
import org.flipkart.Pages.HomePage;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestFlow extends DriverConfig {

    @Test(enabled = false)
    public void homePageFlow() throws InterruptedException, IOException {
        pageContextUI.getHomePage().navigatToHomePage();
        test.log(LogStatus.PASS, capture(DriverSingleton.getDriver(),"HomePage"));
    }

    @Test(enabled = false)
    public void loginPageFlow() throws IOException {
        pageContextUI.getHomePage().navigatToHomePage();
        test.log(LogStatus.PASS, capture(DriverSingleton.getDriver(),"HomePage"));

        pageContextUI.getHomePage().clickLoginButton();
        test.log(LogStatus.PASS, capture(DriverSingleton.getDriver(),"LoginPage"));
    }

    @Test(enabled = true)
    public void searchProduct() throws InterruptedException {
        pageContextUI.getHomePage().searchProduct();
    }
}