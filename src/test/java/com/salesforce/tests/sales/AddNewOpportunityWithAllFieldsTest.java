package com.salesforce.tests.sales;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.enums.opportunity.FieldsNames;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.framework.pages.opportunity.OpportunityDetailsPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.opportunity.FieldsNames.*;

public class AddNewOpportunityWithAllFieldsTest extends BaseTest {
    private Opportunity opportunityAll;
    private OpportunityDetailsPage detailsPage;
    private OpportunitiesPage opportunitiesPage;

    private static final String OPPORTUNITY_NAME = faker.name().title();
    private static final double AMOUNT_VALUE = faker.random().nextInt(10, 100);
    private static final String ORDER_NUMBER_VALUE = faker.code().ean8();
    private static final int PROBABILITY_VALUE = faker.random().nextInt(10, 25);
    private static final String TRACKING_NUMBER_VALUE = faker.code().ean8();
    private static final String DESCRIPTION_VALUE = faker.lorem().fixedString(50);

    @BeforeClass
        public void setOpportunity() {
        opportunityAll = dataProvider.generateAllOpportunityFields(OPPORTUNITY_NAME,
                AMOUNT_VALUE,
                ORDER_NUMBER_VALUE,
                PROBABILITY_VALUE,
                TRACKING_NUMBER_VALUE,
                DESCRIPTION_VALUE);
        opportunitiesPage = BROWSER
                .loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab();
    }

    @Test
    public void verifyAddNewOpportunityWithAllFieldsTest() {
        detailsPage = opportunitiesPage
                .clickOnNewButton()
                .enterAllRequiredFields(opportunityAll)
                .enterAmount(opportunityAll)
                .enterNextStep(opportunityAll)
                .enterOrderNumber(opportunityAll)
                .selectValueInPicklist(TYPE, opportunityAll.getType())
                .selectValueInPicklist(LEAD_SOURCE, opportunityAll.getLeadSource())
                .enterProbability(opportunityAll)
                .enterTrackingNumber(opportunityAll)
                .enterCurrentGenerator(opportunityAll)
                .selectValueInPicklist(DELIVERY_STATUS, opportunityAll.getDeliveryInstallationStatus())
                .enterMainCompetitor(opportunityAll)
                .enterDescription(opportunityAll)
                .saveOpportunity()
                .openOpportunityRecordDetailsTab();

        String actualRecordName = detailsPage.getOpportunityFieldValue(FieldsNames.OPPORTUNITY_NAME);
        String actualStage = detailsPage.getOpportunityFieldValue(STAGE);
        String actualCloseDate = detailsPage.getOpportunityFieldValue(CLOSE_DATE);
        double actualAmount = Double.parseDouble(detailsPage.getOpportunityFieldValue(AMOUNT).replaceAll("€", ""));
        String actualNextStep = detailsPage.getOpportunityFieldValue(NEXT_STEP);
        String actualOrderNumber = detailsPage.getOpportunityFieldValue(ORDER_NUMBER);
        String actualTrackingNumber = detailsPage.getOpportunityFieldValue(TRACKING_NUMBER);
        String actualType = detailsPage.getOpportunityFieldValue(TYPE);
        String actualMainCompetitor = detailsPage.getOpportunityFieldValue(MAIN_COMPETITOR);
        String actualDescription = detailsPage.getOpportunityFieldValue(DESCRIPTION);

        softAssert.assertTrue(detailsPage.isOpportunityRecordLabelDisplayed(opportunityAll.getName()),
                String.format("Opportunity record page 'Label' should be %s", opportunityAll.getName()));
        softAssert.assertEquals(actualRecordName, opportunityAll.getName(),
                String.format("Opportunity 'Name' should be %s", opportunityAll.getName()));
        softAssert.assertEquals(actualStage, opportunityAll.getStage(),
                String.format("Opportunity 'Stage' should be %s", opportunityAll.getStage()));
        softAssert.assertEquals(actualCloseDate, opportunityAll.getCloseDate(),
                String.format("Opportunity 'Close Date' should be %s", opportunityAll.getCloseDate()));
        softAssert.assertEquals(actualAmount, opportunityAll.getAmount(),
                String.format("Opportunity 'Amount' should be %s", opportunityAll.getAmount()));
        softAssert.assertEquals(actualNextStep, opportunityAll.getNextStep(),
                String.format("Opportunity 'Next Step' should be %s", opportunityAll.getNextStep()));
        softAssert.assertEquals(actualOrderNumber, opportunityAll.getOrderNumber(),
                String.format("Opportunity 'Order Number' should be %s", opportunityAll.getOrderNumber()));
        softAssert.assertEquals(actualTrackingNumber, opportunityAll.getTrackingNumber(),
                String.format("Opportunity 'Tracking Number' should be %s", opportunityAll.getTrackingNumber()));
        softAssert.assertEquals(actualType, opportunityAll.getType(),
                String.format("Opportunity 'Type' should be %s", opportunityAll.getType()));
        softAssert.assertEquals(actualMainCompetitor, opportunityAll.getMainCompetitor(),
                String.format("Opportunity 'Main Competitor(s)' should be %s", opportunityAll.getMainCompetitor()));
        softAssert.assertEquals(actualDescription, opportunityAll.getDescription(),
                String.format("Opportunity 'Description' should be %s", opportunityAll.getDescription()));
        softAssert.assertAll();
    }
}
