package com.salesforce.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {

    @Override
    public void openPage() {
        driver.get(BASE_PAGE + "/");
    }

    @Override
    protected void waitUntilLoaded() throws Exception {
        throw new Exception("waitUntilLoaded should be implemented");
    }
}
