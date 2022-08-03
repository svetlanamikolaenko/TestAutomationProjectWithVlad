package com.salesforce.framework.enums;

public enum OpportunityLeadSources {

    WEB("Web"),
    PHONE_INQUIRY("Phone Inquiry"),
    PARTNER_REFERRAL("Partner Referral"),
    PURCHASED_LIST("Purchased List"),
    OTHER("Other");

    private final String leadSource;

    OpportunityLeadSources(String leadSource){
        this.leadSource = leadSource;
    }

    public String getLeadSource(){
        return this.leadSource;
    }
}
