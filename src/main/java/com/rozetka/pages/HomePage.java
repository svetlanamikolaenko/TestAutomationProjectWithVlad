package com.rozetka.pages;
import com.rozetka.AbstractPage;
import com.rozetka.components.LoginPopup;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends AbstractPage {

    @Override
    @Step("Open main page")
    public void openPage(){
        open( BASE_URL + "/");
    }

    @Step("Open Login PopUp")
    public void openLoginPopup(){
        $("[class*='user']").shouldBe(visible).click();
        new LoginPopup();
    }
}
