package com.salesforce.tests.login;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.pages.SetupHomePage;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private Customer customer;

    @BeforeMethod
    public void setUp() {
        customer = Customers.TEST_USER.getCustomer();
        loginPage = new LoginPage();
    }

    @Test(description = "User is able to login")
    public void verifyLoginTest() {
        loginPage.openPage();
        SetupHomePage setupHomePage = loginPage.loginAs(customer);
        Assert.assertEquals(setupHomePage.getHomeTitle(), "Home",
                String.format("'Home' title should be shown"));
    }
}
