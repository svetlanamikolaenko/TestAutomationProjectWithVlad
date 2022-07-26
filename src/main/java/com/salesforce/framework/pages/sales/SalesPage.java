package com.salesforce.framework.pages.sales;

import com.salesforce.framework.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'appName')]/span[@title='Sales']")
    private WebElement salesTitleLabel;

    String salesTabName = "//*[contains(@class, 'navItem')]//*[text()='%s']";

    @Step("Navigate to 'Sales' tab")
    public SalesOpportunitiesRecentlyViewedPage navigateToSalesTab(String tabName){
        waitUntilLoaded();
        jsHelper().clickJS(findElementByXpath(String.format(salesTabName, tabName)));
        return new SalesOpportunitiesRecentlyViewedPage();
    }

    @Override
    public void waitUntilLoaded(){
        waitHelper().waitElementUntilVisible(salesTitleLabel);
    }

}
