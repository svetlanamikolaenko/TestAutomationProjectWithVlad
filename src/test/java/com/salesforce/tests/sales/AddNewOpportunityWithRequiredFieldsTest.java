package com.salesforce.tests.sales;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.framework.pages.opportunity.OpportunityDetailsPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.opportunity.FieldsNames.*;

public class AddNewOpportunityWithRequiredFieldsTest extends BaseTest {

    private Opportunity opportunity;
    private OpportunityDetailsPage detailsPage;
    private OpportunitiesPage opportunitiesPage;

    private static final String OPPORTUNITY_RECORD_NAME = faker.name().title();

    @BeforeMethod
    public void setOpportunity() {
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_RECORD_NAME);
        opportunitiesPage = BROWSER
                .loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab();
    }

    @Test
    public void verifyAddNewOpportunityWithRequiredFieldsTest() {
        detailsPage = opportunitiesPage
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity)
                .saveOpportunity()
                .openOpportunityRecordDetailsTab();

        softAssert.assertTrue(detailsPage.isOpportunityRecordLabelDisplayed(opportunity.getName()),
                String.format("Opportunity record page 'Label' should be %s", opportunity.getName()));
        String actualOpportunityRecordName = detailsPage.getOpportunityFieldValue(OPPORTUNITY_NAME);
        softAssert.assertEquals(actualOpportunityRecordName, opportunity.getName(),
                String.format("Opportunity 'Name' should be %s", opportunity.getName()));
        String actualOpportunityStage = detailsPage.getOpportunityFieldValue(STAGE);
        softAssert.assertEquals(actualOpportunityStage, opportunity.getStage(),
                String.format("Opportunity 'Stage' should be %s", opportunity.getStage()));
        String actualOpportunityCloseDate = detailsPage.getOpportunityFieldValue(CLOSE_DATE);
        softAssert.assertEquals(actualOpportunityCloseDate, opportunity.getCloseDate(),
                String.format("Opportunity 'Close Date' should be %s", opportunity.getCloseDate()));
        softAssert.assertAll();
    }
}
