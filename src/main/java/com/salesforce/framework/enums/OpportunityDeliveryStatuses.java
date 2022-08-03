package com.salesforce.framework.enums;

public enum OpportunityDeliveryStatuses {

    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    YET_TO_BEGIN("Yet to begin");

    private final String status;

    OpportunityDeliveryStatuses(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
