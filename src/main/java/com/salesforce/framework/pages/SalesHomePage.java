package com.salesforce.framework.pages;

import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesHomePage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'appName')]/*[text()='Sales']")
    private WebElement salesTitleLabel;

    private final static String SALES_TAB_LABEL_FORMAT = "//*[contains(@class, 'navItem')]/a[@title='%s']";

    @Step("Navigate to 'Sales' tab")
    public OpportunitiesPage navigateToSalesTab(String tabLabel){
        waitUntilLoaded();
        waitHelper().waitLocatorUntilVisible(String.format(SALES_TAB_LABEL_FORMAT, tabLabel));
        jsHelper().clickJS(findElementByXpath(String.format(SALES_TAB_LABEL_FORMAT, tabLabel)));
        return new OpportunitiesPage();
    }

    @Override
    public void waitUntilLoaded(){
        waitHelper().waitElementUntilVisible(salesTitleLabel);
    }

}
