package com.salesforce.tests.opportunity;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.NewOpportunityPopup;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.opportunity.FieldsNames.*;

public class NewOpportunityRequiredFieldsErrorsTest extends BaseTest {

    private OpportunitiesPage opportunitiesPage;
    private Opportunity opportunity;
    private NewOpportunityPopup newOpportunityPopup;
    private static final String INVALID_CLOSE_DATE = "25.05.2022";

    @BeforeClass
    public void navigateToOpportunities() {
        opportunity = dataProvider.generateOpportunityWithoutNameField();
        opportunitiesPage = BROWSER.loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab();
    }

    @Test(description = "Verify that Form Page Error messages are displayed when required field is empty")
    public void verifyErrorMessageIsDisplayedWhenEmptyRequiredFieldTest() {
        newOpportunityPopup = opportunitiesPage
                .clickOnNewButton()
                .selectValueInPicklist(STAGE, opportunity.getStage())
                .enterValueIntoInputField(CLOSE_DATE, opportunity.getCloseDate())
                .clickOnSaveButton();

        softAssert.assertTrue(newOpportunityPopup.isFormPageErrorDialogDisplayed(),
               "Error message 'We hit a snag.' is not displayed");
        softAssert.assertTrue(newOpportunityPopup.isFieldDisplayedInFormPageDialog(OPPORTUNITY_NAME),
                String.format("Not populated field '%s' should be displayed", OPPORTUNITY_NAME.getFieldLabel()));
        softAssert.assertAll();
    }

    @Test(description = "Verify appropriate error message appears under required fields")
    public void verifyErrorMessagesUnderRequiredFieldsTest() {
        newOpportunityPopup = opportunitiesPage
                .clickOnNewButton()
                .clickOnSaveButton();

        String expectedErrorMessageUnderEmptyRequiredField = "Complete this field.";
        String actualErrorMessageUnderOpportunityNameField = newOpportunityPopup.getErrorMessageUnderField(OPPORTUNITY_NAME);
        String actualErrorMessageUnderStageField = newOpportunityPopup.getErrorMessageUnderField(STAGE);
        String actualErrorMessageUnderCloseDateField = newOpportunityPopup.getErrorMessageUnderField(CLOSE_DATE);

        softAssert.assertEquals(actualErrorMessageUnderStageField, expectedErrorMessageUnderEmptyRequiredField,
                String.format("Error message under empty required field should be '%s'", expectedErrorMessageUnderEmptyRequiredField));
        softAssert.assertEquals(actualErrorMessageUnderOpportunityNameField, expectedErrorMessageUnderEmptyRequiredField,
                String.format("Error message under empty required field should be '%s'", expectedErrorMessageUnderEmptyRequiredField));
        softAssert.assertEquals(actualErrorMessageUnderCloseDateField, expectedErrorMessageUnderEmptyRequiredField,
                String.format("Error message under empty required field should be '%s'", expectedErrorMessageUnderEmptyRequiredField));
        softAssert.assertAll();
    }

    @Test
    public void verifyErrorMessageUnderCloseDateFieldTest(){
        newOpportunityPopup =  opportunitiesPage
                .clickOnNewButton()
                .enterValueIntoInputField(CLOSE_DATE, INVALID_CLOSE_DATE)
                .clickOnSaveButton();

        String expectedErrorMessageUnderCloseDateField = "Your entry does not match the allowed format dd/MM/yyyy.";
        String actualErrorMessageUnderCloseDateField = newOpportunityPopup.getErrorMessageUnderField(CLOSE_DATE);
        Assert.assertEquals(actualErrorMessageUnderCloseDateField, expectedErrorMessageUnderCloseDateField,
                String.format("Error message under empty required field should be '%s'", expectedErrorMessageUnderCloseDateField));
    }

    @AfterMethod
    public void clickCancel() {
        opportunitiesPage = newOpportunityPopup.clickOnCancelButton();
    }
}
