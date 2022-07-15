package com.salesforce.tests.sales;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.framework.pages.SetupHomePage;
import com.salesforce.framework.pages.sales.SalesOpportunityPage;
import com.salesforce.framework.pages.sales.SalesPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewOpportunityTest extends BaseTest {
    private LoginPage loginPage;
    private SalesPage salesPage;

    @BeforeClass
    public void setUp(){
        Customer customer = Customers.TEST_USER.getCustomer();
        loginPage = new LoginPage();
        loginPage =loginPage.openLoginPage();
        SetupHomePage setupHomePage = loginPage.loginAs(customer);
        salesPage = setupHomePage.openSalesApplication();
    }

    @Test
    public void addNewOpportunityTest() throws InterruptedException {
        SalesOpportunityPage salesOpportunityPage = salesPage.navigateToSalesTab("Opportunity");
        salesOpportunityPage.clickNewOpportunityButton();
        salesOpportunityPage.enterOpportunityName("Test");
        salesOpportunityPage.enterCloseDate("21/07/2022");
        salesOpportunityPage.clickOnStagePicklist();
        Thread.sleep(10000);
        salesOpportunityPage.chooseStage();
        Thread.sleep(10000);

    }

}
