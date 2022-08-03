package com.salesforce.framework.data_providers;

import com.salesforce.framework.enums.*;
import com.salesforce.framework.helpers.DateConvertorHelper;
import com.salesforce.framework.models.Opportunity;

import java.time.LocalDate;

public class OpportunityDataProvider {

    public Opportunity generateOpportunityRequiredFields(String opportunityName){
        return Opportunity.newBuilder()
                .setName(opportunityName)
                .withStage(OpportunityStages.NEEDS_ANALYSIS.getStage())
                .withCloseDate(DateConvertorHelper.convertLocalDateToCivilianDateFormat(LocalDate.now()))
                .build();
    }

    public Opportunity generateAllOpportunityFields(String opportunityName,double amount, String orderNumber,
                                                    int probability, String trackingNumber, String description){
        return generateOpportunityRequiredFields(opportunityName)
                .setAmount(amount)
                .setNextStep("Next Step")
                .setOrderNumber(orderNumber)
                .setType(OpportunityTypes.NEW_CUSTOMER.getType())
                .setLeadSource(OpportunityLeadSources.WEB.getLeadSource())
                .setProbability(probability)
                .setTrackingNumber(trackingNumber)
                .setCurrentGenerator("test")
                .setMainCompetitor(MainCompetitors.COKE_COLA.getMainCompetitor())
                .setDeliveryInstallationStatus(OpportunityDeliveryStatuses.YET_TO_BEGIN.getStatus())
                .setDescription(description);
    }
}
