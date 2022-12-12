package com.salesforce.tests.opportunity;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.enums.opportunity.FieldsNames;
import com.salesforce.framework.enums.opportunity.Stages;
import com.salesforce.framework.pages.opportunity.NewOpportunityPopup;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class OpportunityDropDownTest extends BaseTest {
    private NewOpportunityPopup newOpportunityPopup;

    @BeforeMethod
    public void openNewOpportunityPopup() {
        newOpportunityPopup = BROWSER
                .loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab()
                .clickOnNewButton();
    }

    @Test
    public void verifyValuesInStageDropDownTest() {
        List<String> valuesInStageDropDown = newOpportunityPopup
                .clickOnPicklist(FieldsNames.STAGE.getFieldLabel())
                .getValuesInStageDropDown();

        Assert.assertTrue(valuesInStageDropDown.contains(Stages.QUALIFICATION.getStage()));
    }
}
