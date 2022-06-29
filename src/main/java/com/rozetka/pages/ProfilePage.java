package com.rozetka.pages;

import com.codeborne.selenide.SelenideElement;
import com.rozetka.AbstractPage;
import com.rozetka.components.forms.PersonalInfoForm;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage extends AbstractPage {

    SelenideElement personalInfo = $(".personal-section", 0);

    public PersonalInfoForm personalInfoForm(){
        personalInfo.shouldBe(visible).click();
        return new PersonalInfoForm();
    }

    @Override
    @Step("Open customers cabinet")
    public void openPage() {
        open(BASE_URL + "/cabinet/personal-information/");
    }

    @Step("Get customer email")
    public String getUserEmail() {
        return $(".cabinet-user__email").shouldBe(visible).getText();
    }
}
