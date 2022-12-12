package com.salesforce.framework.enums.opportunity;

public enum Stages {

    PROSPECTING("Prospecting"),
    QUALIFICATION("Qualification"),
    NEEDS_ANALYSIS("Needs Analysis"),
    VALUE_PROPOSITION("Value Proposition"),
    ID_DECISION_MAKERS("Id. Decision Makers"),
    PERCEPTION_ANALYSIS("Perception/Analysis"),
    PROPOSAL_PRICE_QUOTE("Proposal Price Quote"),
    NEGOTIATION_REVIEW("Negotiation/Review"),
    CLOSED_WON("Closed Won"),
    CLOSED_LOST("Closed Lost");

    private final String stage;

    Stages(String stage) {
        this.stage = stage;
    }

    public String getStage(){
        return this.stage;
    }
}
