package com.salesforce.framework.pages.opportunity;

import io.qameta.allure.Step;

public class OpportunityDetailsPage extends OpportunityHeaderPage {

    private final static String OPPORTUNITY_FIELD_VALUE_FORMAT = "//*[text()='%s']//ancestor::div[contains(@class,'element_edit')]//*[contains(@class,'field-value')]";

    @Step("Get '{0}' Field value")
    public String getOpportunityFieldValue(String fieldLabel){
        waitHelper().waitLocatorUntilVisible((String.format(OPPORTUNITY_FIELD_VALUE_FORMAT, fieldLabel)));
        return findElementByXpath(String.format(OPPORTUNITY_FIELD_VALUE_FORMAT, fieldLabel)).getText();
    }
}
