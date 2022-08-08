package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.pages.SalesHomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class OpportunitiesPage extends SalesHomePage {

    @FindBy(xpath = "//ul[contains(@class,'actions ')]//*[text()='New']")
    private WebElement newOpportunityButton;

    @FindBy(xpath = "//*[contains(@class,'breadcrumb')]//*[text()='Opportunities']")
    private WebElement opportunityTitleLabel;


    @Step("Click on 'New' button")
    public NewOpportunityPopup clickOnNewButton(){
        waitHelper().waitElementUntilVisible(newOpportunityButton);
        newOpportunityButton.click();
        return new NewOpportunityPopup();
    }

    @Override
    public void waitUntilLoaded() {
        jsHelper().allElementsLoaded();
        waitHelper().waitElementUntilVisible(opportunityTitleLabel);
    }

    public OpportunitiesPage openOpportunitiesPage() {
        driver.get(BASE_PAGE + "/lightning/o/Opportunity/list?filterName=Recent");
        waitUntilLoaded();
        return this;
    }
}
