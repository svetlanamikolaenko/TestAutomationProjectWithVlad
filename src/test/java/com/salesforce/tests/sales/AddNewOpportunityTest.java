package com.salesforce.tests.sales;

import com.github.javafaker.Faker;
import com.salesforce.framework.enums.Opportunities;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.sales.SalesOpportunitiesRecentlyViewedPage;
import com.salesforce.framework.pages.sales.SalesOpportunityRecordPage;
import com.salesforce.framework.pages.sales.SalesPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewOpportunityTest extends BaseTest {
    private SalesPage salesPage;
    private SalesOpportunitiesRecentlyViewedPage recentlyViewedPage;
    private Opportunity opportunity;
    private Opportunity opportunityAll;

    private static final String OPPORTUNITY_RECORD_NAME = new Faker().name().title();
    private static final String CLOSE_DATE = "24/07/2022";
    private static final String STAGE_OPTION = "Needs Analysis";

    private static final String OPPORTUNITY_RECORD_NAME_ALL = new Faker().name().title();
    private static final String CLOSE_DATE_ALL = "25/07/2022";
    private static final String STAGE_OPTION_ALL = "Prospecting";
    private static final double AMOUNT = new Faker().random().nextInt(10, 100);
    private static final String NEXT_STEP = "Test";

    @BeforeMethod
    public void setup() {
        opportunityAll = Opportunities.TEST_OPPORTUNITY.getOpportunity();

        opportunity = Opportunity.newBuilder()
                .withName(OPPORTUNITY_RECORD_NAME)
                .withStage(STAGE_OPTION)
                .withCloseDate(CLOSE_DATE)
                .build();
        salesPage = setupHomePage.openSalesApplication();
    }

    @Test
    public void verifyAddNewOpportunityWithRequiredFieldsTest() {
        recentlyViewedPage = salesPage.navigateToSalesTab("Opportunities");
        SalesOpportunityRecordPage recordPage = recentlyViewedPage
                .enterAllRequiredFields(opportunity)
                .clickOnSaveButton();

        String opportunityRecordLabel = recordPage.getOpportunityRecordLabel();
        softAssert.assertEquals(opportunityRecordLabel, opportunity.getName(),
                String.format("Opportunity record page 'Title' should be %s", opportunity.getName()));

        recordPage.openOpportunityRecordDetailsTab();
        String actualOpportunityRecordName = recordPage.getOpportunityFieldValue("Opportunity Name");
        softAssert.assertEquals(actualOpportunityRecordName, opportunity.getName(),
                String.format("Opportunity 'Name' should be %s", opportunity.getName()));

        String actualOpportunityStage = recordPage.getOpportunityFieldValue("Stage");
        softAssert.assertEquals(actualOpportunityStage, opportunity.getStage(),
                String.format("Opportunity 'Stage' should be %s", opportunity.getStage()));

        String actualOpportunityCloseDate = recordPage.getOpportunityFieldValue("Close Date");
        softAssert.assertEquals(actualOpportunityCloseDate, opportunity.getCloseDate(),
                String.format("Opportunity 'Close Date' should be %s", opportunity.getCloseDate()));
        softAssert.assertAll();
    }

    @Test
    public void verifyAddNewOpportunityWithAllFields() {
        recentlyViewedPage = salesPage.navigateToSalesTab("Opportunities");
        SalesOpportunityRecordPage recordPage = recentlyViewedPage
                .enterAllRequiredFields(opportunityAll)
                .enterAmount(opportunityAll)
                .enterNextStep(opportunityAll)
                .enterOrderNumber(opportunityAll)
                .selectTypeInPicklist(opportunityAll)
                .selectLeadSourceInPicklist(opportunityAll)
                .enterProbability(opportunityAll)
                .enterTrackingNumber(opportunityAll)
                .enterCurrentGenerator(opportunityAll)
                .selectDeliveryStatusPicklist(opportunityAll)
                .enterMainCompetitor(opportunityAll)
                .enterDescription(opportunityAll)
                .clickOnSaveButton();

        String opportunityRecordLabel = recordPage.getOpportunityRecordLabel();
        softAssert.assertEquals(opportunityRecordLabel, opportunityAll.getName(),
                String.format("Opportunity record page 'Title' should be %s", opportunityAll.getName()));

        recordPage.openOpportunityRecordDetailsTab();
        String actualRecordName = recordPage.getOpportunityFieldValue("Opportunity Name");
        String actualStage = recordPage.getOpportunityFieldValue("Stage");
        String actualCloseDate = recordPage.getOpportunityFieldValue("Close Date");
        double actualAmount = Double.parseDouble(recordPage.getOpportunityFieldValue("Amount").replaceAll("€", ""));
        String actualNextStep = recordPage.getOpportunityFieldValue("Next Step");
        int actualOrderNumber = Integer.parseInt(recordPage.getOpportunityFieldValue("Order Number"));

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
        softAssert.assertAll();
    }
}
