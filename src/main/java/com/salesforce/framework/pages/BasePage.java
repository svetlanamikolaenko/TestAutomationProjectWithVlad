package com.salesforce.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {

    public WebElement findElementByXpath(String element){
        return driver.findElement(By.xpath(element));
    }
    @Override
    protected void waitUntilLoaded() throws Exception {
        throw new Exception("waitUntilLoaded should be implemented");
    }
}
