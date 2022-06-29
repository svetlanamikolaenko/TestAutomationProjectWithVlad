package com.rozetka.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.rozetka.framework.browser.Browser;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    @BeforeClass
    @Step("Set Parameters")
    public void setParameters() {
        Browser.setUpBrowser();
    }
}
