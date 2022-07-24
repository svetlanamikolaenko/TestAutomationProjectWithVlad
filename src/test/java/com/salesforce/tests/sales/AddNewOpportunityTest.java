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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddNewOpportunityTest extends BaseTest {
    private LoginPage loginPage;
    private SalesPage salesPage;
    private static final String OPPORTUNITY_RECORD_NAME = new Faker().name().title();
    private static final String CLOSE_DATE = "21/07/2022";
    private static final String STAGE_OPTION = "Needs Analysis";
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
                .build();
        loginPage = new LoginPage();
        loginPage = loginPage.openLoginPage();
        SetupHomePage setupHomePage = loginPage.loginAs(customer);
        salesPage = setupHomePage.openSalesApplication();
    }

    @Test
    public void addNewOpportunityTest() {
        SalesOpportunitiesListPage salesOpportunitiesListPage = salesPage.navigateToSalesTab("Opportunities");
        SalesOpportunityRecordPage recordPage = salesOpportunitiesListPage
                .addNewOpportunity(opportunity);

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
    }


}
