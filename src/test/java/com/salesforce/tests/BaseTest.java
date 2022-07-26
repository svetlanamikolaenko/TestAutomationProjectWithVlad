package com.salesforce.tests;

import com.salesforce.framework.browser.Browser;
import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.helpers.JavaScriptHelper;
import com.salesforce.framework.helpers.WebDriverWaitHelper;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.framework.pages.SetupHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest {
    protected WebDriver driver;
    protected JavaScriptHelper javaScriptHelper;
    protected WebDriverWaitHelper webDriverWaitHelper;
    protected SoftAssert softAssert;
    protected LoginPage loginPage;
    protected SetupHomePage setupHomePage;

    @BeforeClass
    public void setupDriver() {
        driver = Browser.getWebDriver();
        webDriverWaitHelper = new WebDriverWaitHelper();
        javaScriptHelper = new JavaScriptHelper();
        softAssert = new SoftAssert();
        Customer customer = Customers.TEST_USER.getCustomer();
        loginPage = new LoginPage();
        loginPage = loginPage.openLoginPage();
        setupHomePage = loginPage.loginAs(customer);
    }

    @AfterClass(alwaysRun = true)
    public void closeSite() {
        javaScriptHelper.clearLocalStorageJS();
        Browser.closeWebDriver();
    }
}
