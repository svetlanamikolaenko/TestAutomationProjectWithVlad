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
        loginPage = loginPage.openLoginPage();
    }

    @Test(description = "User is able to login")
    public void verifyLoginTest() {
        SetupHomePage setupHomePage = loginPage.loginAs(customer);
        Assert.assertEquals(setupHomePage.getHomeTitle(), "Home",
                String.format("'Home' title should be shown"));
    }

    @Test
    public void verifyOpeningSalesApp() throws InterruptedException {
        SetupHomePage setupHomePage = new SetupHomePage();
        setupHomePage.openSalesApplication();
        Thread.sleep(10000);
    }
}
