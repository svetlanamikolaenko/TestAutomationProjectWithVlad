package com.salesforce.framework.pages;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

    @Override
    @Step("Open main page")
    public void openPage(){
        driver.get(BASE_PAGE + "/lightning/setup/SetupOneHome/home");
        waitUntilLoaded();
    }
}
