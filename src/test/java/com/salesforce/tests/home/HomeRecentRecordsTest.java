package com.salesforce.tests.home;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.HomePage;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HomeRecentRecordsTest extends BaseTest {

    private static final String OPPORTUNITY_RECORD_NAME = faker.name().title();

    private Opportunity opportunity;
    private HomePage homePage;

    @BeforeMethod
    public void setOpportunity() {
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_RECORD_NAME);
        homePage = BROWSER
                .loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab()
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity)
                .saveOpportunity()
                .openOpportunityTab()
                .openHomeTab();
    }

    @Test
    public void verifyIfCreatedRecordIsPresentInRecentRecordsCard() {
        Assert.assertTrue(homePage.getRecentRecordsList().contains(opportunity.getName()),
                String.format("Opportunity with name '%s' should be present in Recent Opportunities", opportunity.getName()));
    }
}
