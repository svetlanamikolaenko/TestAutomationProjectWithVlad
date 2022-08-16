package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpportunityHeaderPage extends HomePage {

    @FindBy(xpath = "//*[text()='Opportunity']")
    private WebElement opportunityTitleLabel;

    @FindBy(xpath = "//a[@data-label='Details']")
    private WebElement opportunityRecordDetailsTab;

    private static final String OPPORTUNITY_RECORD_LABEL_FORMAT = "//*[text()='Opportunity']/..//*[text()='%s']";

    @Step("Opportunity Record Label '{0}' is displayed")
    public boolean isOpportunityRecordLabelDisplayed(String opportunityName) {
        try {
            return findElementByXpath(String.format(OPPORTUNITY_RECORD_LABEL_FORMAT, opportunityName)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Open 'Details' Tab")
    public OpportunityDetailsPage openOpportunityRecordDetailsTab(){
        waitHelper().waitElementUntilVisible(opportunityRecordDetailsTab);
        opportunityRecordDetailsTab.click();
        return new OpportunityDetailsPage();
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(opportunityTitleLabel);
    }
}
