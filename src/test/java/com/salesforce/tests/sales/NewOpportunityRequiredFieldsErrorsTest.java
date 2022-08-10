package com.salesforce.tests.sales;

import com.salesforce.framework.data_providers.OpportunityDataProvider;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.NewOpportunityPopup;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.OpportunityFieldsNames.*;

public class NewOpportunityRequiredFieldsErrorsTest extends BaseTest {

    private OpportunitiesPage opportunitiesPage;
    private Opportunity opportunity;
    private Opportunity opportunityEmpty;
    private NewOpportunityPopup newOpportunityPopup;

    @BeforeClass
    public void navigateToOpportunities() {
        dataProvider = new OpportunityDataProvider();
        opportunity = dataProvider.generateOpportunityWithoutNameField();
        opportunityEmpty = dataProvider.generateOpportunityWithoutRequiredFields(faker.lorem().fixedString(20));
        opportunitiesPage = new OpportunitiesPage();
        opportunitiesPage = opportunitiesPage.openOpportunitiesPage();
    }

    @AfterMethod
    public void clickCancel() {
        newOpportunityPopup.clickOnCancelButton();
    }

    @Test(description = "Verify that Form Page Error messages are displayed when required field is empty")
    public void verifyErrorMessageIsDisplayedWhenEmptyRequiredField() {
        newOpportunityPopup = opportunitiesPage
                .clickOnNewButton()
                .selectValueInPicklist(STAGE.getFieldLabel(), opportunity.getStage())
                .enterCloseDate(opportunity.getCloseDate());
        newOpportunityPopup.clickOnSaveButton();

        String expectedErrorMessage = "We hit a snag.";
        String actualFormPageErrorMessage = newOpportunityPopup.getFormPageErrorDialogHeaderText();
        String actualFormFieldErrorMessage = newOpportunityPopup.getFormFieldErrorText();

        softAssert.assertEquals(actualFormPageErrorMessage, expectedErrorMessage,
                String.format("Not populated field '%s' should be displayed", expectedErrorMessage));
        softAssert.assertTrue(actualFormFieldErrorMessage.contains(OPPORTUNITY_NAME.getFieldLabel()),
                String.format("Not populated field '%s' should be displayed", OPPORTUNITY_NAME.getFieldLabel()));
        softAssert.assertAll();
    }

    @Test(description = "Verify that new Opportunity is not created when required fields is empty")
    public void verifyOpportunityIsNotCreatedWithoutRequitedField() {
        newOpportunityPopup = opportunitiesPage
                .clickOnNewButton()
                .enterOpportunityName(opportunityEmpty.getName());
        newOpportunityPopup.clickOnSaveButton();

        String actualFormFieldErrorMessage = newOpportunityPopup.getFormFieldErrorText();
        softAssert.assertTrue(actualFormFieldErrorMessage.contains(CLOSE_DATE.getFieldLabel()),
                String.format("Not populated field '%s' should be displayed", CLOSE_DATE.getFieldLabel()));
        softAssert.assertTrue(actualFormFieldErrorMessage.contains(STAGE.getFieldLabel()),
                String.format("Not populated field '%s' should be displayed", STAGE.getFieldLabel()));
        softAssert.assertAll();
    }

    @Test(description = "Verify appropriate error message appears under required fields")
    public void verifyErrorMessagesUnderRequiredFields() {
        newOpportunityPopup = opportunitiesPage.clickOnNewButton();
        newOpportunityPopup.clickOnSaveButton();

        String expectedErrorMessageUnderEmptyRequiredField = "Complete this field.";

        String actualErrorMessageUnderOpportunityNameField = newOpportunityPopup.getErrorMessageUnderRequiredField(OPPORTUNITY_NAME.getFieldLabel());
        String actualErrorMessageUnderStageField = newOpportunityPopup.getErrorMessageUnderRequiredField(STAGE.getFieldLabel());
        String actualErrorMessageUnderCloseDateField = newOpportunityPopup.getErrorMessageUnderRequiredField(CLOSE_DATE.getFieldLabel());

        softAssert.assertEquals(actualErrorMessageUnderStageField, expectedErrorMessageUnderEmptyRequiredField,
                String.format("Error message under empty required field should be %s", expectedErrorMessageUnderEmptyRequiredField));
        softAssert.assertEquals(actualErrorMessageUnderOpportunityNameField, expectedErrorMessageUnderEmptyRequiredField,
                String.format("Error message under empty required field should be %s", expectedErrorMessageUnderEmptyRequiredField));
        softAssert.assertEquals(actualErrorMessageUnderCloseDateField, expectedErrorMessageUnderEmptyRequiredField,
                String.format("Error message under empty required field should be %s", expectedErrorMessageUnderEmptyRequiredField));


        String invalidCloseDate = "25.05.2022";
        newOpportunityPopup.enterValuesInField(CLOSE_DATE.getFieldLabel(), invalidCloseDate);
        newOpportunityPopup.clickOnSaveButton();

        String expectedErrorMessageUnderCloseDateField = "Your entry does not match the allowed format dd/MM/yyyy.";
        actualErrorMessageUnderCloseDateField = newOpportunityPopup.getErrorMessageUnderRequiredField(CLOSE_DATE.getFieldLabel());
        softAssert.assertTrue(actualErrorMessageUnderCloseDateField.contains(expectedErrorMessageUnderCloseDateField),
                String.format("Error message under empty required field should be %s", expectedErrorMessageUnderCloseDateField));
        softAssert.assertAll();
    }
}
