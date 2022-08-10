package com.salesforce.tests.sales;

import com.salesforce.framework.enums.*;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.SalesHomePage;
import com.salesforce.framework.pages.opportunity.NewOpportunityPopup;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.framework.pages.opportunity.OpportunityDetailsPage;
import com.salesforce.framework.pages.opportunity.OpportunityHeaderPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.salesforce.framework.enums.OpportunityFieldsNames.*;

public class AddNewOpportunityTest extends BaseTest {

    private Opportunity opportunity;
    private Opportunity opportunityAll;
    private NewOpportunityPopup newOpportunityPopup;
    private OpportunityDetailsPage detailsPage;
    private OpportunityHeaderPage headerPage;

    private static final String OPPORTUNITY_RECORD_NAME = faker.name().title();
    private static final String OPPORTUNITY_RECORD_NAME_ALL = faker.name().title();
    private static final double AMOUNT_VALUE = faker.random().nextInt(10, 100);
    private static final String ORDER_NUMBER_VALUE = faker.code().ean8();
    private static final int PROBABILITY_VALUE = faker.random().nextInt(10, 25);
    private static final String TRACKING_NUMBER_VALUE = faker.code().ean8();
    private static final String DESCRIPTION_VALUE = faker.lorem().fixedString(50);
    private OpportunitiesPage opportunitiesPage;

    @BeforeClass
    public void setupData() {
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_RECORD_NAME);
        opportunityAll = dataProvider.generateAllOpportunityFields(OPPORTUNITY_RECORD_NAME_ALL,
                AMOUNT_VALUE,
                ORDER_NUMBER_VALUE,
                PROBABILITY_VALUE,
                TRACKING_NUMBER_VALUE,
                DESCRIPTION_VALUE);
        SalesHomePage salesHomePage = setupHomePage.openSalesApplication();
        opportunitiesPage = new OpportunitiesPage();
        opportunitiesPage = salesHomePage.navigateToSalesTab(SalesTabLabels.OPPORTUNITIES.getTabLabel());
    }

    @Test(priority = 1)
    public void verifyAddNewOpportunityWithRequiredFieldsTest() {
        newOpportunityPopup = opportunitiesPage
                .openOpportunitiesPage()
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity);
        newOpportunityPopup.clickOnSaveButton();

        headerPage = new OpportunityHeaderPage();
        softAssert.assertTrue(headerPage.isOpportunityRecordLabelDisplayed(opportunity.getName()),
                String.format("Opportunity record page 'Label' should be %s", opportunity.getName()));

        detailsPage = headerPage.openOpportunityRecordDetailsTab();
        String actualOpportunityRecordName = detailsPage.getOpportunityFieldValue(OPPORTUNITY_NAME.getFieldLabel());
        softAssert.assertEquals(actualOpportunityRecordName, opportunity.getName(),
                String.format("Opportunity 'Name' should be %s", opportunity.getName()));

        String actualOpportunityStage = detailsPage.getOpportunityFieldValue(STAGE.getFieldLabel());
        softAssert.assertEquals(actualOpportunityStage, opportunity.getStage(),
                String.format("Opportunity 'Stage' should be %s", opportunity.getStage()));

        String actualOpportunityCloseDate = detailsPage.getOpportunityFieldValue(CLOSE_DATE.getFieldLabel());
        softAssert.assertEquals(actualOpportunityCloseDate, opportunity.getCloseDate(),
                String.format("Opportunity 'Close Date' should be %s", opportunity.getCloseDate()));
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void verifyAddNewOpportunityWithAllFieldsTest() {
        newOpportunityPopup = opportunitiesPage
                .openOpportunitiesPage()
                .clickOnNewButton()
                .enterAllRequiredFields(opportunityAll)
                .enterAmount(opportunityAll)
                .enterNextStep(opportunityAll)
                .enterOrderNumber(opportunityAll)
                .selectValueInPicklist(TYPE.getFieldLabel(),opportunityAll.getType())
                .selectValueInPicklist(LEAD_SOURCE.getFieldLabel(), opportunityAll.getLeadSource())
                .enterProbability(opportunityAll)
                .enterTrackingNumber(opportunityAll)
                .enterCurrentGenerator(opportunityAll)
                .selectValueInPicklist(DELIVERY_STATUS.getFieldLabel(), opportunityAll.getDeliveryInstallationStatus())
                .enterMainCompetitor(opportunityAll)
                .enterDescription(opportunityAll);
        newOpportunityPopup.clickOnSaveButton();

        headerPage = new OpportunityHeaderPage();
        softAssert.assertTrue(headerPage.isOpportunityRecordLabelDisplayed(opportunityAll.getName()),
                String.format("Opportunity record page 'Label' should be %s", opportunityAll.getName()));

        detailsPage = headerPage.openOpportunityRecordDetailsTab();

        String actualRecordName = detailsPage.getOpportunityFieldValue(OPPORTUNITY_NAME.getFieldLabel());
        String actualStage = detailsPage.getOpportunityFieldValue(STAGE.getFieldLabel());
        String actualCloseDate = detailsPage.getOpportunityFieldValue(CLOSE_DATE.getFieldLabel());
        double actualAmount = Double.parseDouble(detailsPage.getOpportunityFieldValue(AMOUNT.getFieldLabel()).replaceAll("€", ""));
        String actualNextStep = detailsPage.getOpportunityFieldValue(NEXT_STEP.getFieldLabel());
        String actualOrderNumber = detailsPage.getOpportunityFieldValue(ORDER_NUMBER.getFieldLabel());
        String actualTrackingNumber = detailsPage.getOpportunityFieldValue(TRACKING_NUMBER.getFieldLabel());
        String actualType = detailsPage.getOpportunityFieldValue(TYPE.getFieldLabel());
        String actualMainCompetitor = detailsPage.getOpportunityFieldValue(MAIN_COMPETITOR.getFieldLabel());
        String actualDescription = detailsPage.getOpportunityFieldValue(DESCRIPTION.getFieldLabel());

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
