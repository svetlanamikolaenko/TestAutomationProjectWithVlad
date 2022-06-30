package com.salesforce.tests;

import com.salesforce.framework.browser.Browser;
import com.salesforce.framework.helpers.JavaScriptHelper;
import com.salesforce.framework.helpers.WebDriverWaitHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest {
    protected WebDriver driver;
    protected JavaScriptHelper javaScriptHelper;
    protected WebDriverWaitHelper webDriverWaitHelper;
    protected SoftAssert softAssert;

    @BeforeClass
    public void setupDriver() {
        driver = Browser.getWebDriver();
        webDriverWaitHelper = new WebDriverWaitHelper();
        javaScriptHelper = new JavaScriptHelper();
        softAssert = new SoftAssert();
    }

    @AfterClass(alwaysRun=true)
    public void closeSite() {
        javaScriptHelper.clearLocalStorageJS();
        Browser.closeWebDriver();
    }
}
