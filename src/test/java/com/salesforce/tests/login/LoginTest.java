package com.salesforce.tests.login;

import com.salesforce.tests.BaseTest;
import org.testng.Assert;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "User is able to login")
    public void verifyLoginTest() {
        Assert.assertEquals(setupHomePage.getHomeTitle(), "Home",
                String.format("'Home' title should be shown"));
    }
}
