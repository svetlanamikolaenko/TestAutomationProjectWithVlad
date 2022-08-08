package com.salesforce.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class BasePage extends AbstractPage {

    public WebElement findElementByXpath(String element){
        return driver.findElement(By.xpath(element));
    }
    @Override
    protected void waitUntilLoaded() throws Exception {
        throw new Exception("waitUntilLoaded should be implemented");
    }
}
