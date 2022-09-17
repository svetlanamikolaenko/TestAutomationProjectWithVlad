package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.opportunity.FieldsNames;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpportunityDetailsPage extends OpportunityHeaderPage {

    @FindBy(xpath = "//*[text()='Opportunity Name']//parent::div//following-sibling::div//*[contains(@class, 'field-value')]")
    private WebElement opportunityNameFieldValue;

    @FindBy(xpath = "//*[text()='Opportunity Name']//parent::div//following-sibling::div//*[contains(@class, 'field-value')]//following-sibling::button/*[contains(@class,'icon')]")
    private WebElement opportunityNameEditButton;

    private final static String OPPORTUNITY_FIELD_VALUE_FORMAT = "//*[text()='%s']//ancestor::div[contains(@class,'element_edit')]//*[contains(@class,'field-value')]";

    @Step("Get the '{label.fieldLabel}' field value")
    public String getOpportunityFieldValue(FieldsNames label) {
        String fieldLabel = label.getFieldLabel();
        waitHelper().waitLocatorUntilVisible((String.format(OPPORTUNITY_FIELD_VALUE_FORMAT, fieldLabel)));
        return findElementByXpath(String.format(OPPORTUNITY_FIELD_VALUE_FORMAT, fieldLabel)).getText();
    }

    @Override
    public void waitUntilLoaded() {
        super.waitUntilLoaded();
    }
}
