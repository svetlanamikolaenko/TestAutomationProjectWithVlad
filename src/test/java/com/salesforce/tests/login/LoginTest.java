package com.salesforce.tests.login;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.pages.HomePage;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private HomePage homePage;
    @BeforeClass
    public void loginTo() {
      homePage = BROWSER.loginAs(Customers.TEST_USER.getCustomer());
    }

    @Test(description = "User is able to login")
    public void verifyLoginTest() {
        Assert.assertEquals(homePage.getHomeTitle(), "Home",
                "'Home' title should be shown");
    }
}
