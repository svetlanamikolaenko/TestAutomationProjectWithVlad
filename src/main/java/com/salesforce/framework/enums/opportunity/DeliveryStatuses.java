package com.salesforce.framework.enums.opportunity;

public enum DeliveryStatuses {

    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    YET_TO_BEGIN("Yet to begin");

    private final String status;

    DeliveryStatuses(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
