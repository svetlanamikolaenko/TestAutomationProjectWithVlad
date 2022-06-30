package com.salesforce.framework.enums;

import com.salesforce.framework.config.TestConfig;
import com.salesforce.framework.models.Customer;

public enum Customers {
    TEST_USER(Customer.newBuilder()
            .withEmail(TestConfig.CONFIG.userEmail())
            .withPassword(TestConfig.CONFIG.userPassword())
            .build());

    private final Customer customer;

    Customers(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
