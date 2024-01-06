package org.flipkart.Utils;

import org.flipkart.DriverSingleton.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementTypes {
    public static WebElement getWebElement(String identifierType, By by) {
      return  switch (identifierType) {
            case "XPATH", "CSS", "ID", "Name", "TagName" -> DriverSingleton.getDriver().findElement(by);
            default -> null;
        };
    }

//    private List<WebElement> getWebElements(String identifierType, String identifierValue) {
//        return switch (identifierType) {
//            case "XPATH" -> DriverSingleton.getInstance().getDriver().findElements(By.xpath(identifierValue));
//            case "CSS" -> DriverSingleton.getInstance().getDriver().findElements(By.cssSelector(identifierValue));
//            case "ID" -> DriverSingleton.getInstance().getDriver().findElements(By.id(identifierValue));
//            case "Name" -> DriverSingleton.getInstance().getDriver().findElements(By.name(identifierValue));
//            case "TagName" -> DriverSingleton.getInstance().getDriver().findElements(By.tagName(identifierValue));
//            default -> null;
//        };
//    }
}
