package com.rozetka.components;

import com.rozetka.models.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class LoginPopup {
    By submitButton = By.cssSelector("[class*='auth-modal__submit']");

    @Step("Enter Email")
    public LoginPopup fillEmail(String email) {
        $("#auth_email").shouldBe(visible).val(email);
        return this;
    }

    @Step("Enter Password")
    public LoginPopup fillPassword(String password) {
        $("#auth_pass").shouldBe(visible).val(password);
        return this;
    }

    @Step("Click on Submit")
    public void clickOnSubmit() {
        $(submitButton).shouldBe(visible).click();
        sleep(20000);
        $(submitButton).click();
        $(submitButton).shouldNotBe(visible);
    }

    @Step("Login as customer")
    public void loginAs(Customer customer) {
        fillEmail(customer.getEmail());
        fillPassword(customer.getPassword());
        clickOnSubmit();
    }
}
