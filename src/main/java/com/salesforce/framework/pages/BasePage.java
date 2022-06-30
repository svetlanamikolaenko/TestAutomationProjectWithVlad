package com.salesforce.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {

    @FindBy(xpath = "//h1")
    public WebElement caption;

    @Step("Get page title")
    public String getCaption() {
        waitUntilLoaded();
        return caption.getText();
    }

    @Override
    public void openPage() {
        driver.get(BASE_PAGE + "/");
        waitUntilLoaded();
    }

    @Override
    protected void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(caption);
    }
}
