package com.salesforce.framework.enums.opportunity;

public enum LeadSources {

    WEB("Web"),
    PHONE_INQUIRY("Phone Inquiry"),
    PARTNER_REFERRAL("Partner Referral"),
    PURCHASED_LIST("Purchased List"),
    OTHER("Other");

    private final String leadSource;

    LeadSources(String leadSource){
        this.leadSource = leadSource;
    }

    public String getLeadSource(){
        return this.leadSource;
    }
}
