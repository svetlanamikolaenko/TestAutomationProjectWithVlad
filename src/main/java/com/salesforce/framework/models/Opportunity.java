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

    private Opportunity(final Builder builder) {
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

    public static Builder createNew(String name, String stage, String closeDate) {
        return new Builder(name, stage, closeDate);
    }
    public static Builder createNew() {
        return new Builder();
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

        private Builder(String name, String stage, String closeDate) {
            this.name = name;
            this.stage = stage;
            this.closeDate = closeDate;
        }

        private Builder() {

        }

        public Builder setName(String val) {
            this.name = val;
            return this;
        }

        public Builder setStage(String val) {
            this.stage = val;
            return this;
        }

        public Builder setCloseDate(String val) {
            this.closeDate = val;
            return this;
        }

        public Builder setAmount(double val) {
            amount = val;
            return this;
        }

        public Builder setNextStep(String val) {
            nextStep = val;
            return this;
        }

        public Builder setType(String val) {
            type = val;
            return this;
        }

        public Builder setProbability(int val) {
            probability = val;
            return this;
        }

        public Builder setLeadSource(String val) {
            leadSource = val;
            return this;
        }

        public Builder setOrderNumber(String val) {
            orderNumber = val;
            return this;
        }

        public Builder setTrackingNumber(String val) {
            trackingNumber = val;
            return this;
        }

        public Builder setCurrentGenerator(String val) {
            currentGenerator = val;
            return this;
        }

        public Builder setMainCompetitor(String val) {
            mainCompetitor = val;
            return this;
        }

        public Builder setDeliveryInstallationStatus(String val) {
            deliveryInstallationStatus = val;
            return this;
        }

        public Builder setDescription(String val) {
            description = val;
            return this;
        }

        public Opportunity build() {
            return new Opportunity(this);
        }
    }
}
