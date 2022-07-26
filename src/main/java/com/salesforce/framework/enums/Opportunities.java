package com.salesforce.framework.enums;

import com.github.javafaker.Faker;
import com.salesforce.framework.models.Opportunity;

public enum Opportunities {
    TEST_OPPORTUNITY(Opportunity.newBuilder()
            .withName(new Faker().name().title())
            .withStage(OpportunityConstants.QUALIFICATION_STAGE)
            .withCloseDate("26/07/2022")
            .withAmount(new Faker().random().nextInt(10, 100))
            .withNextStep(new Faker().name().prefix())
            .withOrderNumber(new Faker().random().nextInt(1000000, 9999999))
            .withType("New Customer")
            .withLeadSource("Web")
            .withProbability(new Faker().random().nextInt(10, 25))
            .withTrackingNumber(new Faker().code().ean13())
            .withCurrentGenerator("test")
            .withMainCompetitor(new Faker().company().name())
            .withDeliveryStatus("In progress")
            .withDescription(new Faker().lorem().fixedString(50))
            .build());

    private  final Opportunity opportunity;

    Opportunities(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public Opportunity getOpportunity(){
        return opportunity;
    }

}
