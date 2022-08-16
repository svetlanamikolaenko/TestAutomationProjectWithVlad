package com.salesforce.framework.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SetupHomePage extends AbstractPage {

    @FindBy(xpath = "//h1//*[text()='Home']")
    private WebElement homeTitleLabel;

    @FindBy(xpath = "//div[contains(@class, 'appLauncher')]")
    private WebElement appLauncherIcon;

    @FindBy(xpath = "//a[@data-label='Sales']")
    private WebElement salesAppLabel;

    @Step("Get 'Home' title")
    public String getHomeTitle() {
        return homeTitleLabel.getText();
    }

    @Step("Open Sales application")
    public HomePage openSalesApplication() {
        waitHelper().waitElementUntilVisible(appLauncherIcon);
        appLauncherIcon.click();
        waitHelper().waitElementUntilVisible(salesAppLabel);
        jsHelper().clickJS(salesAppLabel);
        return new HomePage();
    }

    @Override
    public void waitUntilLoaded(){
        waitHelper().waitElementUntilVisible(homeTitleLabel);
    }
}
