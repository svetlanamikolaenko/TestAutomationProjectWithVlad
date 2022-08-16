package com.salesforce.framework.pages;

import com.salesforce.framework.enums.SalesTabLabels;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@title='Home']")
    private WebElement homeTitleLabel;

    private final static String SALES_TAB_LABEL_FORMAT = "//*[contains(@class, 'navItem')]/a[@title='%s']";

    @Step("Navigate to '{tab.tabLabel}' tab")
    public void navigateToSalesTab(SalesTabLabels tab){
        String tabLabel = tab.getTabLabel();
        waitHelper().waitLocatorUntilVisible(String.format(SALES_TAB_LABEL_FORMAT, tabLabel));
        jsHelper().clickJS(findElementByXpath(String.format(SALES_TAB_LABEL_FORMAT, tabLabel)));
    }

    public OpportunitiesPage openOpportunityTab(){
        navigateToSalesTab(SalesTabLabels.OPPORTUNITIES);
        return new OpportunitiesPage();
    }

    @Step("Get 'Home Page' title")
    public String getHomeTitle() {
        return homeTitleLabel.getText();
    }

    @Override
    protected void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(homeTitleLabel);
    }
}
