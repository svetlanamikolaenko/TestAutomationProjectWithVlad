package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class OpportunitiesPage extends HomePage {

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
        waitHelper().waitElementUntilVisible(opportunityTitleLabel);
    }
}
