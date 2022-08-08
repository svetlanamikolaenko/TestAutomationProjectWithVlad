package com.salesforce.tests.sales;

import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.NewOpportunityPopup;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.OpportunityFieldsNames.*;

public class NewOpportunityNegativeTest extends BaseTest {

    private OpportunitiesPage opportunitiesPage;
    private Opportunity opportunity;
    private Opportunity opportunityEmpty;
    private NewOpportunityPopup newOpportunityPopup;

    @BeforeMethod
    public void setupData() {
        opportunity = dataProvider.generateOpportunityWithoutNameField();
        opportunityEmpty = dataProvider.generateOpportunityWithoutRequiredFields(faker.lorem().fixedString(20));
        setupHomePage.openSalesApplication();
        opportunitiesPage = new OpportunitiesPage();
    }

    @AfterMethod
    public void clickCancel() {
        newOpportunityPopup.clickOnCancelButton();
    }

    @Test(description = "Verify that Form Page Error messages are displayed when required field is empty")
    public void verifyErrorMessageIsDisplayedWhenEmptyRequiredField() {
        opportunitiesPage.openOpportunitiesPage();
        newOpportunityPopup = opportunitiesPage
                .clickOnNewButton()
                .selectValueInPicklist(STAGE.getFieldLabel(), opportunity.getStage())
                .enterCloseDate(opportunity.getCloseDate());
        newOpportunityPopup.clickOnSaveButton();

        String expectedErrorMessage = "We hit a snag.";
        String actualFormPageErrorMessage = newOpportunityPopup.getFormPageErrorDialogHeaderText();
        String actualFormFieldErrorMessage = newOpportunityPopup.getFormFieldErrorText();

        softAssert.assertEquals(actualFormPageErrorMessage, expectedErrorMessage,
                String.format("Error message '%s' should be displayed", expectedErrorMessage));
        softAssert.assertTrue(actualFormFieldErrorMessage.contains(OPPORTUNITY_NAME.getFieldLabel()),
                String.format("Field '%s' that is not populated should be displayed", OPPORTUNITY_NAME.getFieldLabel()));
        softAssert.assertAll();
    }

    @Test(description = "Verify that new Opportunity is not created when required fields is empty")
    public void verifyOpportunityIsNotCreatedWithoutRequitedField() {
        newOpportunityPopup = opportunitiesPage
                .openOpportunitiesPage()
                .clickOnNewButton()
                .enterOpportunityName(opportunityEmpty.getName());
        newOpportunityPopup.clickOnSaveButton();

        String actualFormFieldErrorMessage = newOpportunityPopup.getFormFieldErrorText();
        softAssert.assertTrue(actualFormFieldErrorMessage.contains(CLOSE_DATE.getFieldLabel()),
                String.format("Field '%s' that is not populated should be displayed", CLOSE_DATE.getFieldLabel()));
        softAssert.assertTrue(actualFormFieldErrorMessage.contains(STAGE.getFieldLabel()),
                String.format("Field '%s' that is not populated should be displayed", STAGE.getFieldLabel()));
        softAssert.assertAll();
    }
}
