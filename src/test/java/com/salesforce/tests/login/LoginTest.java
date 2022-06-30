package com.salesforce.tests.login;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.pages.HomePage;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private HomePage homePage;
    private LoginPage loginPage;
    Customer customer;

    @BeforeMethod
    public void setUp() {
        customer = Customers.TEST_USER.getCustomer();
        loginPage = new LoginPage();
    }

    @Test(description = "User is able to login")
    public void verifyLoginTest() {
        loginPage.openPage();
        homePage = loginPage.loginAs(customer);
        String actualHomePageTitle = homePage.getTitle();
        Assert.assertEquals(actualHomePageTitle, "Home | Salesforce", String.format("Actual page title is:  ", homePage.getTitle()));
    }
}
