package com.salesforce.framework.helpers;

import com.salesforce.framework.browser.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

    public void clickJS(WebElement element) {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("arguments[0].click();",
                element);
    }

    public void allElementsLoaded() {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("return document.readyState").equals("complete");
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("arguments[0].scrollIntoView(true)",
                element);
    }

    public void clearLocalStorageJS() {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("window.localStorage.clear()");
    }
}
