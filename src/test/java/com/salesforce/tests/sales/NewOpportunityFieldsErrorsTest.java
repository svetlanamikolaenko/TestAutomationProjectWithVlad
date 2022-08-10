package com.salesforce.tests.sales;

import com.salesforce.framework.enums.SalesTabLabels;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.SalesHomePage;
import com.salesforce.framework.pages.opportunity.NewOpportunityPopup;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.OpportunityFieldsNames.*;


public class NewOpportunityFieldsErrorsTest extends BaseTest {

    private static final String OPPORTUNITY_RECORD_NAME = faker.name().title();
    private static final String PROBABILITY_VALUE = "120";
    private static final String AMOUNT_VALUE = "100000000000000000";

    private OpportunitiesPage opportunitiesPage;
    private Opportunity opportunity;
    private NewOpportunityPopup newOpportunityPopup;
    private SalesHomePage salesHomePage;


    @BeforeClass
    public void setupData() {
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_RECORD_NAME);
        salesHomePage = setupHomePage.openSalesApplication();
        opportunitiesPage = new OpportunitiesPage();
        opportunitiesPage = salesHomePage.navigateToSalesTab(SalesTabLabels.OPPORTUNITIES.getTabLabel());
    }

    @AfterMethod
    public void clickCancel() {
        newOpportunityPopup.clickOnCancelButton();
    }

    @Test(description = "Verify appropriate error message appears under fields")
    public void verifyErrorMessageUnderNotRequiredFields(){
        newOpportunityPopup = opportunitiesPage
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity);
        newOpportunityPopup.enterValuesInField(PROBABILITY.getFieldLabel(), PROBABILITY_VALUE);
        newOpportunityPopup.clickOnSaveButton();

        String expectedErrorMessageUnderProbabilityField = "Probability must be between 0 and 100: Probability (%)";
        String actualErrorMessageUnderProbabilityField = newOpportunityPopup.getErrorMessageUnderRequiredField(PROBABILITY.getFieldLabel());
        softAssert.assertEquals(actualErrorMessageUnderProbabilityField, expectedErrorMessageUnderProbabilityField,
                String.format("Error message under empty required field should be %s", expectedErrorMessageUnderProbabilityField));
        softAssert.assertAll();

        newOpportunityPopup.enterValuesInField(AMOUNT.getFieldLabel(), AMOUNT_VALUE);
        newOpportunityPopup.clickOnSaveButton();

        String expectedErrorMessageUnderAmountField = "Amount: value outside of valid range on numeric field:";
        String actualErrorMessageUnderAmountField = newOpportunityPopup.getErrorMessageUnderRequiredField(AMOUNT.getFieldLabel());
        softAssert.assertTrue(actualErrorMessageUnderAmountField.contains(expectedErrorMessageUnderAmountField),
                String.format("Error message under empty required field should be %s", expectedErrorMessageUnderAmountField));
        softAssert.assertAll();
    }

}
