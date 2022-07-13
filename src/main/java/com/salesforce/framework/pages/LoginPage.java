package com.salesforce.framework.pages;

import com.salesforce.framework.models.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='theloginform']")
    private WebElement loginForm;

    @Step("Enter '{0}' Email")
    private void setEmail(String email) {
        userNameField.clear();
        userNameField.sendKeys(email);
    }

    @Step("Enter '{0}' Password")
    private void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Click On Login Button")
    public void clickOnLoginButton() {
        loginButton.click();
    }

    @Step("Login as customer")
    public SetupHomePage loginAs(Customer customer){
        setEmail(customer.getEmail());
        setPassword(customer.getPassword());
        clickOnLoginButton();
        return new SetupHomePage();
    }

    @Override
    public void openPage() {
        driver.get(BASE_PAGE + "/");
        waitUntilLoaded();
    }

    @Override
    protected void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(loginForm);
    }
}
