package com.salesforce.framework.data_providers;

import com.salesforce.framework.enums.opportunity.*;
import com.salesforce.framework.helpers.DateConvertorHelper;
import com.salesforce.framework.models.Opportunity;

import java.time.LocalDate;

public class OpportunityDataProvider {

    private static final String TODAY_DATE = DateConvertorHelper.convertLocalDateToCivilianDateFormat(LocalDate.now());

    public Opportunity generateOpportunityRequiredFields(String opportunityName) {
        return Opportunity.createNew(opportunityName, Stages.NEEDS_ANALYSIS.getStage(), TODAY_DATE).build();
    }

    public Opportunity generateAllOpportunityFields(String opportunityName,
                                                    double amount,
                                                    String orderNumber,
                                                    int probability,
                                                    String trackingNumber,
                                                    String description) {
        return Opportunity.createNew(opportunityName, Stages.NEEDS_ANALYSIS.getStage(), TODAY_DATE)
                .setAmount(amount)
                .setNextStep("Next Step")
                .setOrderNumber(orderNumber)
                .setType(Types.NEW_CUSTOMER.getType())
                .setLeadSource(LeadSources.WEB.getLeadSource())
                .setProbability(probability)
                .setTrackingNumber(trackingNumber)
                .setCurrentGenerator("test")
                .setMainCompetitor(MainCompetitors.COKE_COLA.getMainCompetitor())
                .setDeliveryInstallationStatus(DeliveryStatuses.YET_TO_BEGIN.getStatus())
                .setDescription(description)
                .build();
    }

    public Opportunity generateOpportunityWithoutNameField() {
        return Opportunity.createNew()
                .setStage(Stages.NEEDS_ANALYSIS.getStage())
                .setCloseDate(TODAY_DATE)
                .build();
    }

    public Opportunity generateOpportunityWithTrackingNumber(String opportunityName, String trackingNumber) {
        return Opportunity.createNew(opportunityName, Stages.NEEDS_ANALYSIS.getStage(), TODAY_DATE)
                .setTrackingNumber(trackingNumber).
                build();
    }
}
