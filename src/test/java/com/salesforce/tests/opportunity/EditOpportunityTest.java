package com.salesforce.tests.opportunity;

import com.salesforce.framework.enums.Customers;
import com.salesforce.framework.enums.opportunity.FieldsNames;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.opportunity.OpportunityDetailsPage;
import com.salesforce.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class EditOpportunityTest extends BaseTest {
    private Opportunity opportunity;
    private OpportunityDetailsPage detailsPage;

    private static final String OPPORTUNITY_NAME = faker.name().title();
    private static final String TRACKING_NUMBER = faker.code().ean8();
    private static final String CHANGED_TRACKING_NUMBER = faker.code().ean8();

    @BeforeClass
    public void setOpportunity() {
        opportunity = dataProvider.generateOpportunityRequiredFields(OPPORTUNITY_NAME);
        detailsPage = BROWSER.loginAs(Customers.TEST_USER.getCustomer())
                .openOpportunityTab()
                .clickOnNewButton()
                .enterAllRequiredFields(opportunity)
                .enterTrackingNumber(opportunity.setTrackingNumber(TRACKING_NUMBER))
                .saveOpportunity();
    }

    @Test
    public void verifyEditOpportunity() throws InterruptedException {
        detailsPage = detailsPage
                .openOpportunityTab()
                .openOpportunityEditPopup(opportunity)
                .changeValueInField(FieldsNames.TRACKING_NUMBER, CHANGED_TRACKING_NUMBER)
                .saveOpportunity()
                .searchForOpportunity(opportunity)
                .openOpportunityDetails(opportunity)
                .openOpportunityRecordDetailsTab();

        String actualTrackingNumber = detailsPage.getOpportunityFieldValue(FieldsNames.TRACKING_NUMBER);
        softAssert.assertEquals(actualTrackingNumber, CHANGED_TRACKING_NUMBER, String.format("Opportunity tracking number should be %s", CHANGED_TRACKING_NUMBER));
        softAssert.assertAll();
    }
}
