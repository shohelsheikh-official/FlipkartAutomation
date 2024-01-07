package org.flipkart.DriverSingleton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class DriverSingleton {

    // static WebDriver driver;
    private static DriverSingleton instanceOfSingletonClass = null;

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s | %s | %s | %s", java.time.LocalDateTime.now(), type, message, status));
    }

    private DriverSingleton() {
//        logStatus("Driver", "Driver Initialisation", "Started");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        logStatus("Driver", "Driver Initialisation", "Success");
    }

//    public static DriverSingleton getInstance() {
//        if (instanceOfSingletonClass == null) {
//            instanceOfSingletonClass = new DriverSingleton();
//        }
//        return instanceOfSingletonClass;
//    }

    public static void setDriver(String browser) {
       // WebDriver driver = createDriver(browser);
        driverThreadLocal.set(createDriver(browser));
    }

    private static WebDriver createDriver(String browser) {
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            logStatus("Driver", "Chrome Driver Initialisation", "Started");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            logStatus("Driver", "Chrome Driver Initialisation", "Success");
        } else if (browser.equalsIgnoreCase("safari")) {
            logStatus("Driver", " Safari Driver Initialisation", "Started");
            driver = new SafariDriver();
            driver.manage().window().maximize();
            logStatus("Driver", "Safari Driver Initialisation", "Success");
        } else {
            logStatus("Driver", "Driver Initialisation", "Please Check Browser type");
            throw new IllegalArgumentException("Invalid Browser type");
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            logStatus("Driver", "Quitting", "Success");
            driverThreadLocal.remove();
        } else {
            logStatus("Driver", "Quitting", "Failure");
        }
    }

    @AfterSuite(alwaysRun = true)
    public static void clearThread(){
        driverThreadLocal.remove();
    }
}

