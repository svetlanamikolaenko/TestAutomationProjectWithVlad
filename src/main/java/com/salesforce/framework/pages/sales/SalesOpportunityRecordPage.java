package com.salesforce.framework.pages.sales;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesOpportunityRecordPage extends SalesPage{

    @FindBy(xpath = "//h1//*[@slot='primaryField']")
    private WebElement opportunityRecordLabel;

    @FindBy(xpath = "//*[@id='detailTab__item']")
    private WebElement opportunityRecordDetailsTab;

    @Step("Get Opportunity Record Label")
    public String getOpportunityRecordLabel(){
        waitHelper().waitElementUntilVisible(opportunityRecordLabel);
        return opportunityRecordLabel.getText();
    }

    @Step("Open Opportunity Record Details Tab")
    public void openOpportunityRecordDetailsTab(){
        waitHelper().waitElementUntilVisible(opportunityRecordDetailsTab);
        opportunityRecordDetailsTab.click();
    }

    @Step("Get '{0}' Field value")
    public String getOpportunityFieldValue(String fieldName){
        String opportunityFieldValue = "//*[text()='%s']//ancestor::div[contains(@class,'element_edit')]//*[contains(@class,'field-value')]";
        waitHelper().waitLocatorUntilVisible((String.format(opportunityFieldValue, fieldName)));
        return findElementByXpath(String.format(opportunityFieldValue, fieldName)).getText();
    }
}
