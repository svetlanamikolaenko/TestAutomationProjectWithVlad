package com.salesforce.framework.pages;
import com.salesforce.framework.pages.sales.SalesPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SetupHomePage extends BasePage {

    @FindBy(xpath = "//h1//*[text()='Home']")
    private WebElement homeTitleLabel;

    @FindBy(xpath = "//div[contains(@class, 'appLauncher')]")
    private WebElement appLauncherIcon;

    @FindBy(xpath = "//a[@data-label='Sales']")
    private WebElement salesAppLabel;

    @Step("Get 'Home' title")
    public String getHomeTitle() {
        waitUntilLoaded();
        return homeTitleLabel.getText();
    }

    @Step("Open Sales application")
    public SalesPage openSalesApplication() {
        waitHelper().waitElementUntilVisible(appLauncherIcon);
        appLauncherIcon.click();
        waitHelper().waitElementUntilVisible(salesAppLabel);
        jsHelper().clickJS(salesAppLabel);
        return new SalesPage();
    }

    @Override
    public void waitUntilLoaded(){
        waitHelper().waitElementUntilVisible(homeTitleLabel);
    }
}
