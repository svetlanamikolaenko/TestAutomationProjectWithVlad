package com.salesforce.framework.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SetupHomePage extends BasePage {

    @FindBy(xpath = "//h1//*[text()='Home']")
    private WebElement homeTitleLabel;

    @Step("Get 'Home' title")
    public String getHomeTitle() {
        waitUntilLoaded();
        return homeTitleLabel.getText();
    }

    @Override
    @Step("Open main page")
    public void openPage(){
        driver.get(BASE_PAGE + "/lightning/setup/SetupOneHome/home");
        waitUntilLoaded();
    }

    @Override
    public void waitUntilLoaded(){
        waitHelper().waitElementUntilVisible(homeTitleLabel);
    }
}
