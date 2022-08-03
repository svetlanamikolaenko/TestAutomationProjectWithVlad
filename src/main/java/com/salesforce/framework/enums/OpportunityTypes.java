package com.salesforce.framework.enums;

public enum OpportunityTypes {

    EXISTING_CUSTOMER_UPGRADE("Existing Customer - Upgrade"),
    EXISTING_CUSTOMER_REPLACEMENT("Existing Customer - Replacement"),
    EXISTING_CUSTOMER_DOWNGRADE("Existing Customer - Downgrade"),
    NEW_CUSTOMER("New Customer");

    private final String type;

    OpportunityTypes(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
