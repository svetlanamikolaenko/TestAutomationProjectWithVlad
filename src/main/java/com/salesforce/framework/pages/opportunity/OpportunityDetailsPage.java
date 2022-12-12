package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.opportunity.FieldsNames;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpportunityDetailsPage extends OpportunityHeaderPage {

    private final static String OPPORTUNITY_FIELD_FORMAT = "//*[contains(@class,'edit')][descendant::*[text()='%s']]";
    private final static String OPPORTUNITY_FIELD_VALUE_FORMAT = OPPORTUNITY_FIELD_FORMAT + "//*[contains(@class,'field-value')]";

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
