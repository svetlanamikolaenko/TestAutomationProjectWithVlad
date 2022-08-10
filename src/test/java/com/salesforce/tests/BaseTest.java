package com.salesforce.tests;

import com.github.javafaker.Faker;
import com.salesforce.framework.browser.Browser;
import com.salesforce.framework.data_providers.OpportunityDataProvider;
import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.helpers.JavaScriptHelper;
import com.salesforce.framework.helpers.WebDriverWaitHelper;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.framework.pages.SetupHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest {
    protected WebDriver driver;
    protected JavaScriptHelper javaScriptHelper;
    protected WebDriverWaitHelper webDriverWaitHelper;
    protected SoftAssert softAssert;
    protected LoginPage loginPage;
    protected SetupHomePage setupHomePage;
    public static Faker faker = new Faker();
    protected Customer customer;
    protected OpportunityDataProvider dataProvider;

    @BeforeSuite(alwaysRun = true)
    public void setDriver(){
        driver = Browser.getWebDriver();
        webDriverWaitHelper = new WebDriverWaitHelper();
        javaScriptHelper = new JavaScriptHelper();
        softAssert = new SoftAssert();
        customer= Customers.TEST_USER.getCustomer();
        dataProvider = new OpportunityDataProvider();
        loginPage = new LoginPage();
        loginPage = loginPage.openLoginPage();
        setupHomePage = loginPage.loginAs(customer);
    }

    @AfterSuite(alwaysRun = true)
    public void closeSite() {
        javaScriptHelper.clearLocalStorageJS();
        Browser.closeWebDriver();
    }
}
