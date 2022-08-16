package com.salesforce.tests;

import com.github.javafaker.Faker;
import com.salesforce.framework.browser.Browser;
import com.salesforce.framework.data_providers.OpportunityDataProvider;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.salesforce.framework.browser.Browser.closeWebDriver;

public abstract class BaseTest {
    protected static final Browser BROWSER = new Browser();
    protected static SoftAssert softAssert;
    protected static OpportunityDataProvider dataProvider;
    protected static Faker faker = new Faker();

    @BeforeSuite(alwaysRun = true)
    public void setDriver() {
        softAssert = new SoftAssert();
        dataProvider = new OpportunityDataProvider();
    }

    @AfterSuite(alwaysRun = true)
    public void closeDriver() {
        closeWebDriver();
    }
}
