package com.salesforce.tests.opportunity;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class OpportunitiesTest extends BaseTest {
    private static final String OPPORTUNITY_RECORD_NAME = faker.name().title();

    private Opportunity opportunity;
    private OpportunitiesPage opportunitiesPage;

    @BeforeMethod
    public void setOpportunity() {
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_RECORD_NAME);
        opportunitiesPage = BROWSER
                .loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab()
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity)
                .saveOpportunity()
                .openOpportunityTab();
    }

    @Test
    public void verifyAddedRecordIsDisplayedFirstInOpportunitiesTest() {
        List<String> opportunitiesList = opportunitiesPage.getOpportunityRecordsNames();

        softAssert.assertTrue(opportunitiesList.contains(opportunity.getName()));
        softAssert.assertEquals(opportunitiesPage.getOpportunityRecordsNames().get(0), opportunity.getName());
        softAssert.assertAll();
    }

    @Test
    public void verifyOpeningFirstOpportunityWithDuplicatedName() {
        String firstOpportunityIdWithName = opportunitiesPage.getFirstOpportunityIdWithName(OPPORTUNITY_RECORD_NAME);
        opportunitiesPage.openFirstOpportunityWithName(OPPORTUNITY_RECORD_NAME);

        Assert.assertTrue(BROWSER.getCurrentUrl().contains(firstOpportunityIdWithName),
                "User should navigated to opportunity with id: " + firstOpportunityIdWithName);
    }
}
