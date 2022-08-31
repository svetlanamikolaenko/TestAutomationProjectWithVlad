package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.opportunity.FieldsNames;
import io.qameta.allure.Step;

public class OpportunityDetailsPage extends OpportunityHeaderPage {

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
