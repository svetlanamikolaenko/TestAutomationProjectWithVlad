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

    public void scrollToBottom() {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToTop() {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("window.scrollBy(0, -document.body.scrollHeight);");
    }

    public void refreshBrowser() {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("location.reload()");
    }

    public void clearLocalStorageJS() {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("window.localStorage.clear()");
    }
}
