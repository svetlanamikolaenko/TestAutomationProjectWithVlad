package com.salesforce.tests.opportunity;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.NewOpportunityPopup;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.opportunity.FieldsNames.AMOUNT;
import static com.salesforce.framework.enums.opportunity.FieldsNames.PROBABILITY;

public class NewOpportunityFieldsErrorsTest extends BaseTest {

    private static final String OPPORTUNITY_RECORD_NAME = faker.name().title();
    private static final String PROBABILITY_VALUE = "120";
    private static final String AMOUNT_VALUE = "100000000000000000";

    private OpportunitiesPage opportunitiesPage;
    private Opportunity opportunity;
    private NewOpportunityPopup newOpportunityPopup;

    @BeforeClass
    public void navigateToOpportunities() {
        opportunitiesPage = BROWSER
                .loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab();
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_RECORD_NAME);
    }

    @Test(description = "Verify appropriate error message appears under fields")
    public void verifyErrorMessageUnderNotRequiredFieldsTest(){
        newOpportunityPopup = opportunitiesPage
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity)
                .enterValueIntoInputField(PROBABILITY, PROBABILITY_VALUE)
                .clickOnSaveButton();

        String expectedErrorMessageUnderAmountField = "Amount: value outside of valid range on numeric field:";
        String expectedErrorMessageUnderProbabilityField = "Probability must be between 0 and 100: Probability (%)";
        String actualErrorMessageUnderProbabilityField = newOpportunityPopup.getErrorMessageUnderField(PROBABILITY);

        newOpportunityPopup = newOpportunityPopup
                .enterValueIntoInputField(AMOUNT, AMOUNT_VALUE)
                .clickOnSaveButton();

        String actualErrorMessageUnderAmountField = newOpportunityPopup.getErrorMessageUnderField(AMOUNT);

        softAssert.assertEquals(actualErrorMessageUnderProbabilityField, expectedErrorMessageUnderProbabilityField,
                String.format("Error message under empty required field should be '%s'", expectedErrorMessageUnderProbabilityField));
        softAssert.assertTrue(actualErrorMessageUnderAmountField.contains(expectedErrorMessageUnderAmountField),
                String.format("Error message under empty required field should be '%s'", expectedErrorMessageUnderAmountField));
        softAssert.assertAll();
    }


    @AfterClass
    public void clickCancel() {
        opportunitiesPage = newOpportunityPopup.clickOnCancelButton();
    }
}
