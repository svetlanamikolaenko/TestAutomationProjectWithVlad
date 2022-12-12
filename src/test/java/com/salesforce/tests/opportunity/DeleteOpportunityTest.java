package com.salesforce.tests.opportunity;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import com.salesforce.framework.pages.opportunity.OpportunityDetailsPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteOpportunityTest extends BaseTest {
    private Opportunity opportunity;
    private OpportunityDetailsPage detailsPage;
    private OpportunitiesPage opportunitiesPage;

    private static final String OPPORTUNITY_NAME = faker.name().title();

    @BeforeClass
    public void setOpportunity() {
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_NAME);
        detailsPage = BROWSER.loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab()
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity)
                .saveOpportunity();
    }

    @Test
    public void verifyDeleteOpportunityTest() {
        opportunitiesPage = detailsPage
                .openOpportunityTab()
                .openDeleteOpportunityDialog(opportunity)
                .deleteOpportunity()
                .searchForOpportunity(opportunity);

        softAssert.assertFalse(opportunitiesPage.isOpportunityDisplayed(opportunity),
                String.format("Opportunity '%s' should not be displayed",opportunity.getName()));
        softAssert.assertAll();
    }
}
