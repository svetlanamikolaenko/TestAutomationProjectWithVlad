package com.rozetka.tests.login;

import com.github.javafaker.Faker;
import com.rozetka.components.LoginPopup;
import com.rozetka.enums.Customers;
import com.rozetka.models.Customer;
import com.rozetka.pages.HomePage;
import com.rozetka.pages.ProfilePage;
import com.rozetka.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Locale;

public class LoginTest extends BaseTest {
    HomePage homePage;
    LoginPopup loginPopup;
    ProfilePage profilePage;
    Customer customer;
    Faker faker;
    Locale locale = new Locale("uk_UA");
    String expectedLastName = "";

    @BeforeMethod
    public void setUp() {
        customer = Customers.TEST_USER.getCustomer();
        faker = new Faker(locale);
        homePage = new HomePage();
        loginPopup = new LoginPopup();
        profilePage = new ProfilePage();
        homePage.openPage();
        expectedLastName = faker.name().lastName();
    }

    @Test(description = "User is able to login")
    public void verifyLoginTest() {
        homePage.openLoginPopup();
        loginPopup.loginAs(customer);
        profilePage.openPage();
        String actualUserEmail = profilePage.getUserEmail();
        Assert.assertEquals(actualUserEmail, customer.getEmail());
    }

    @Test(description = "User is able to change surname after login",
            dependsOnMethods = {"verifyLoginTest"})
    public void verifySurnameChanging(){
        profilePage.openPage();
        profilePage.personalInfoForm()
                .edit()
                .changeSurname(expectedLastName)
                .save();
        Assert.assertEquals(profilePage.personalInfoForm().getActualSurname(), expectedLastName);
    }
}
