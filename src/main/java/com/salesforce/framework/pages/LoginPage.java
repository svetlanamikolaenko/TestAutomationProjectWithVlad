package com.salesforce.framework.pages;

import com.salesforce.framework.browser.Browser;
import com.salesforce.framework.models.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='theloginform']")
    private WebElement loginForm;

    @Step("Enter '{0}' Email")
    private LoginPage setEmail(String email) {
        waitHelper().waitElementUntilVisible(userNameField);
        userNameField.clear();
        userNameField.sendKeys(email);
        return this;
    }

    @Step("Enter '{0}' Password")
    private LoginPage setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Click on Login Button")
    public void clickOnLoginButton() {
        loginButton.click();
    }

    @Step("Login as '{customer.email}','{customer.password}'")
    public HomePage loginAsUserAndOpenHomePage(Customer customer) {
        setEmail(customer.getEmail());
        setPassword(customer.getPassword());
        clickOnLoginButton();
        driver.get(Browser.getBaseUrl());
        return new HomePage();
    }

    @Step("Login page is opened")
    public boolean isOpened() {
        try {
            return loginForm.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    protected void waitUntilLoaded() {
        jsHelper().allElementsLoaded();
    }
}
