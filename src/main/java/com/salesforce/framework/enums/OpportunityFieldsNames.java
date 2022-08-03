package com.salesforce.framework.enums;

public enum OpportunityFieldsNames {

    OPPORTUNITY_NAME("Opportunity Name", "Name"),
    CLOSE_DATE("Close Date", "CloseDate"),
    AMOUNT("Amount", "Amount"),
    NEXT_STEP("Next Step", "NextStep"),
    ID_DECISION_MAKERS("Id. Decision Makers","IdDecisionMakers"),
    TRACKING_NUMBER("Tracking Number", "TrackingNumber__c"),
    ORDER_NUMBER("Order Number", "OrderNumber__c"),
    PROBABILITY("Probability (%)", "Probability"),
    CURRENT_GENERATOR("Current Generator(s)", "CurrentGenerators__c"),
    MAIN_COMPETITOR("Main Competitor(s)", "MainCompetitors__c"),
    DESCRIPTION("Description", "Description"),
    TYPE("Type", "Type"),
    LEAD_SOURCE("Lead Source", "LeadSource"),
    STAGE("Stage", "StageName"),
    DELIVERY_STATUS("Delivery/Installation Status", "DeliveryInstallationStatus__c"),
    PRIVATE("Private", "IsPrivate"),
    OPPORTUNITY_OWNER("Opportunity Owner", "OwnerId");

    private final String fieldLabel;
    private final String fieldName;

    OpportunityFieldsNames(String fieldLabel, String fieldName) {
        this.fieldLabel = fieldLabel;
        this.fieldName = fieldName;
    }

    public String getFieldLabel(){
        return this.fieldLabel;
    }

    public String getFieldName(){
        return this.fieldName;
    }
}
