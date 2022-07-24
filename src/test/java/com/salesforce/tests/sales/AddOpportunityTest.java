package com.salesforce.tests.sales;

import com.github.javafaker.Faker;
import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.framework.pages.SetupHomePage;
import com.salesforce.framework.pages.sales.SalesOpportunitiesListPage;
import com.salesforce.framework.pages.sales.SalesOpportunityRecordPage;
import com.salesforce.framework.pages.sales.SalesPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddOpportunityTest extends BaseTest {
    private SalesPage salesPage;
    private static final String OPPORTUNITY_RECORD_NAME = new Faker().name().title();
    private static final String CLOSE_DATE = "21/07/2022";
    private static final String STAGE_OPTION = "Needs Analysis";
    private static final double AMOUNT = new Faker().random().nextInt(10, 100);
    private static final String NEXT_STEP = "Test";
    private SoftAssert softAssert;
    private Opportunity opportunity;

    @BeforeClass
    public void setUp() {
        softAssert = new SoftAssert();
        Customer customer = Customers.TEST_USER.getCustomer();
        opportunity = Opportunity.newBuilder()
                .withName(OPPORTUNITY_RECORD_NAME)
                .withStage(STAGE_OPTION)
                .withCloseDate(CLOSE_DATE)
                .withAmount(AMOUNT)
                .withNextStep(NEXT_STEP)
                .build();
        LoginPage loginPage = new LoginPage().openLoginPage();
        SetupHomePage setupHomePage = loginPage.loginAs(customer);
        salesPage = setupHomePage.openSalesApplication();
    }

    @Test
    public void addNewOpportunityWithFillingInAllFields() {
        SalesOpportunitiesListPage salesOpportunitiesListPage = salesPage.navigateToSalesTab("Opportunities");
        SalesOpportunityRecordPage recordPage = salesOpportunitiesListPage
                .addOpportunity(opportunity);

        String opportunityRecordLabel = recordPage.getOpportunityRecordLabel();
        softAssert.assertEquals(opportunityRecordLabel, opportunity.getName(),
                String.format("Opportunity record page 'Title' should be %s", opportunity.getName()));

        recordPage.openOpportunityRecordDetailsTab();
        String actualRecordName = recordPage.getOpportunityFieldValue("Opportunity Name");
        String actualStage = recordPage.getOpportunityFieldValue("Stage");
        String actualCloseDate = recordPage.getOpportunityFieldValue("Close Date");
        double actualAmount = Double.parseDouble(recordPage.getOpportunityFieldValue("Amount").replaceAll("€", ""));
        String actualNextStep = recordPage.getOpportunityFieldValue("Next Step");

        softAssert.assertEquals(actualRecordName, opportunity.getName(),
                String.format("Opportunity 'Name' should be %s", opportunity.getName()));
        softAssert.assertEquals(actualStage, opportunity.getStage(),
                String.format("Opportunity 'Stage' should be %s", opportunity.getStage()));
        softAssert.assertEquals(actualCloseDate, opportunity.getCloseDate(),
                String.format("Opportunity 'Close Date' should be %s", opportunity.getCloseDate()));
        softAssert.assertEquals(actualAmount, opportunity.getAmount(),
                String.format("Opportunity 'Amount' should be %s", opportunity.getAmount()));
        softAssert.assertEquals(actualNextStep, opportunity.getNextStep(),
                String.format("Opportunity 'Next Step' should be %s", opportunity.getNextStep()));
        softAssert.assertAll();
    }
}

