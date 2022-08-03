package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.pages.SalesHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpportunityHeaderPage extends SalesHomePage {

    @FindBy(xpath = "//*[text()='Opportunity']")
    private WebElement opportunityTitleLabel;

    @FindBy(xpath = "//*[@id='detailTab__item']")
    private WebElement opportunityRecordDetailsTab;

    private static final String OPPORTUNITY_RECORD_LABEL_FORMAT = "//*[text()='Opportunity']/..//*[text()='%s']";

    @Step("Get Opportunity Record Label")
    public boolean isOpportunityRecordLabelDisplayed(String opportunityName){
        waitUntilLoaded();
        waitHelper().waitLocatorUntilVisible(String.format(OPPORTUNITY_RECORD_LABEL_FORMAT, opportunityName));
        return findElementByXpath(String.format(OPPORTUNITY_RECORD_LABEL_FORMAT, opportunityName)).isDisplayed();
    }

    @Step("Open Opportunity Record Details Tab")
    public OpportunityDetailsPage openOpportunityRecordDetailsTab(){
        waitHelper().waitElementUntilVisible(opportunityRecordDetailsTab);
        jsHelper().clickJS(opportunityRecordDetailsTab);
        return new OpportunityDetailsPage();
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(opportunityTitleLabel);
    }
}
