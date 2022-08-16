package com.salesforce.framework.enums.opportunity;

public enum Types {

    EXISTING_CUSTOMER_UPGRADE("Existing Customer - Upgrade"),
    EXISTING_CUSTOMER_REPLACEMENT("Existing Customer - Replacement"),
    EXISTING_CUSTOMER_DOWNGRADE("Existing Customer - Downgrade"),
    NEW_CUSTOMER("New Customer");

    private final String type;

    Types(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
