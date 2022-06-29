package com.rozetka.framework.browser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.rozetka.framework.config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;


public class Browser {
    protected static final String BROWSER_NAME = TestConfig.CONFIG.browser();

    public static void setUpBrowser() {
        Configuration.browser = BROWSER_NAME;
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .includeSelenideSteps(true)
                .screenshots(true)
                .savePageSource(true));
    }
}
