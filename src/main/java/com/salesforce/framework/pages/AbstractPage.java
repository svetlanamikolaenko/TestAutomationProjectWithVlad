package com.salesforce.framework.pages;

import com.salesforce.framework.browser.Browser;
import com.salesforce.framework.helpers.JavaScriptHelper;
import com.salesforce.framework.helpers.WebDriverWaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage() {
        driver = Browser.getWebDriver();
        PageFactory.initElements(driver, this);
        waitUntilLoaded();
    }

    protected abstract void waitUntilLoaded();

    public WebDriverWaitHelper waitHelper() {
        return new WebDriverWaitHelper();
    }

    public JavaScriptHelper jsHelper() {
        return new JavaScriptHelper();
    }

    public WebElement findElementByXpath(String element){
        return driver.findElement(By.xpath(element));
    }
}
