package com.salesforce.tests.sales;

import com.github.javafaker.Faker;
import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.framework.pages.SetupHomePage;
import com.salesforce.framework.pages.sales.SalesOpportunityPage;
import com.salesforce.framework.pages.sales.SalesPage;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewOpportunityTest extends BaseTest {
    private LoginPage loginPage;
    private SalesPage salesPage;
    private static final String OPPORTUNITY_NAME = new Faker().name().title();
    private static final String CLOSE_DATE = "21/07/2022";
    private static final String STAGE_OPTION = "Needs Analysis";

    @BeforeClass
    public void setUp() {
        Customer customer = Customers.TEST_USER.getCustomer();
        loginPage = new LoginPage();
        loginPage = loginPage.openLoginPage();
        SetupHomePage setupHomePage = loginPage.loginAs(customer);
        salesPage = setupHomePage.openSalesApplication();
    }

    @Test
    public void addNewOpportunityTest() {
        SalesOpportunityPage salesOpportunityPage = salesPage.navigateToSalesTab("Opportunities");
        salesOpportunityPage.addNewOpportunity(OPPORTUNITY_NAME, CLOSE_DATE, STAGE_OPTION);

        String actualOpportunityName = salesOpportunityPage.getOpportunityName();
        Assert.assertEquals(actualOpportunityName, OPPORTUNITY_NAME,
                String.format("Opportunity Name should be %s", OPPORTUNITY_NAME));
    }
}
