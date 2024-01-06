package org.flipkart.Pages;


import org.flipkart.Helpers.Constants;
import org.flipkart.PageElements.HomePageElements;
import org.flipkart.Utils.ElementTypes;
import org.flipkart.context.PageContextUI;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageContextUI implements HomePageElements {
    static WebDriver driver;

    public HomePage(WebDriver driver){
        HomePage.driver = driver;
    }

    public void navigatToHomePage(){
        driver.get(Constants.homePageURL);
    }
    public void clickLoginButton(){
        ElementTypes.getWebElement("XPATH", HomePageElements.loginButton).click();
    }
}
