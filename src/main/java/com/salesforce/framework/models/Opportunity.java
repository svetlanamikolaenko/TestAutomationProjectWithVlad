package com.salesforce.framework.models;

public class Opportunity {
    private String name;
    private String stage;
    private String closeDate;
    private double amount;
    private String nextStep;
    private String type;
    private String leadSource;
    private int probability;
    private int orderNumber;
    private String trackingNumber;
    private String currentGenerator;
    private String mainCompetitor;
    private String deliveryStatus;
    private String description;


    public String getName() {
        return name;
    }

    public String getStage() {
        return stage;
    }

    public String getCloseDate() {
        return closeDate;
    }


    public double getAmount() {
        return amount;
    }

    public String getNextStep() {
        return nextStep;
    }

    public String getType() {
        return type;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public int getProbability() {
        return probability;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getCurrentGenerator() {
        return currentGenerator;
    }

    public String getMainCompetitor() {
        return mainCompetitor;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getDescription() {
        return description;
    }

    public Opportunity() {
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public Opportunity(final Builder builder){
        name = builder.name;
        stage = builder.stage;
        closeDate = builder.closeDate;
        amount = builder.amount;
        nextStep = builder.nextStep;
        type = builder.type;
        leadSource = builder.leadSource;
        probability = builder.probability;
        orderNumber = builder.orderNumber;
        trackingNumber = builder.trackingNumber;
        currentGenerator = builder.currentGenerator;
        mainCompetitor = builder.mainCompetitor;
        deliveryStatus = builder.deliveryStatus;
        description = builder.description;
    }

    public static final class Builder {
        private String name;
        private String stage;
        private String closeDate;
        private double amount;
        private String nextStep;
        private String type;
        private String leadSource;
        private int probability;
        private int orderNumber;
        private String trackingNumber;
        private String currentGenerator;
        private String mainCompetitor;
        private String deliveryStatus;
        private String description;

        private Builder(){
        }

        public Builder withName(final String val) {
            name = val;
            return this;
        }

        public Builder withStage (final String val) {
            stage = val;
            return this;
        }

        public Builder withCloseDate(final String val){
            closeDate = val;
            return this;
        }

        public Builder withAmount(final double val) {
            amount = val;
            return this;
        }

        public Builder withNextStep(final String val) {
            nextStep = val;
            return this;
        }

        public Builder withType(final String val) {
            type = val;
            return this;
        }

        public Builder withProbability(final int val) {
            probability = val;
            return this;
        }

        public Builder withLeadSource(final String val) {
            leadSource = val;
            return this;
        }

        public Builder withOrderNumber(final int val) {
            orderNumber = val;
            return this;
        }

        public Builder withTrackingNumber(final String val){
            trackingNumber = val;
            return this;
        }

        public Builder withCurrentGenerator(final String val){
            currentGenerator = val;
            return this;
        }

        public Builder withMainCompetitor(final String val){
            mainCompetitor = val;
            return this;
        }

        public Builder withDeliveryStatus(final String val){
            deliveryStatus = val;
            return this;
        }

        public Builder withDescription(final String val) {
            description = val;
            return this;
        }

        public Opportunity build(){
            return new Opportunity(this);
        }
    }
}
