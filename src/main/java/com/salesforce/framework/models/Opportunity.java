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
    private String orderNumber;
    private String trackingNumber;
    private String currentGenerator;
    private String mainCompetitor;
    private String deliveryInstallationStatus;
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

    public String getOrderNumber() {
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

    public String getDeliveryInstallationStatus() {
        return deliveryInstallationStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public Opportunity setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Opportunity setNextStep(String nextStep) {
        this.nextStep = nextStep;
        return this;
    }

    public Opportunity setType(String type) {
        this.type = type;
        return this;
    }

    public Opportunity setLeadSource(String leadSource) {
        this.leadSource = leadSource;
        return this;
    }

    public Opportunity setProbability(int probability) {
        this.probability = probability;
        return this;
    }

    public Opportunity setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public Opportunity setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public Opportunity setCurrentGenerator(String currentGenerator) {
        this.currentGenerator = currentGenerator;
        return this;
    }

    public Opportunity setMainCompetitor(String mainCompetitor) {
        this.mainCompetitor = mainCompetitor;
        return this;
    }

    public Opportunity setDeliveryInstallationStatus(String deliveryInstallationStatus) {
        this.deliveryInstallationStatus = deliveryInstallationStatus;
        return this;
    }

    public Opportunity setDescription(String description) {
        this.description = description;
        return this;
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
        deliveryInstallationStatus = builder.deliveryInstallationStatus;
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
        private String orderNumber;
        private String trackingNumber;
        private String currentGenerator;
        private String mainCompetitor;
        private String deliveryInstallationStatus;
        private String description;

        private Builder(){
        }

        public Builder setName(String val) {
            name = val;
            return this;
        }

        public Builder withStage (String val) {
            stage = val;
            return this;
        }

        public Builder withCloseDate(String val){
            closeDate = val;
            return this;
        }

        public Builder withAmount(double val) {
            amount = val;
            return this;
        }

        public Builder withNextStep(String val) {
            nextStep = val;
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Builder withProbability(int val) {
            probability = val;
            return this;
        }

        public Builder withLeadSource(String val) {
            leadSource = val;
            return this;
        }

        public Builder withOrderNumber(String val) {
            orderNumber = val;
            return this;
        }

        public Builder withTrackingNumber(String val){
            trackingNumber = val;
            return this;
        }

        public Builder withCurrentGenerator(String val){
            currentGenerator = val;
            return this;
        }

        public Builder withMainCompetitor(String val){
            mainCompetitor = val;
            return this;
        }

        public Builder withDeliveryStatus(String val){
            deliveryInstallationStatus = val;
            return this;
        }

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Opportunity build(){
            return new Opportunity(this);
        }
    }
}
