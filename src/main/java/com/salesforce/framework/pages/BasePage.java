package com.salesforce.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {

    @FindBy(xpath = "//title")
    public WebElement title;

    @Step("Get page title")
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public void openPage() {
        driver.get(BASE_PAGE + "/");
    }

    @Override
    protected void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(title);
    }
}
