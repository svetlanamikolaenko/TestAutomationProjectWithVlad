package com.rozetka.models;

public class Customer {
    private String email;
    private String password;
    private String name;
    private String lastName;

    public Customer() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer(final Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.lastName = builder.lastName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String email;
        private String password;
        private String name;
        private String lastName;

        private Builder() {

        }

        public Builder withEmail(final String val) {
            this.email = val;
            return this;
        }

        public Builder withPassword(final String val) {
            this.password = val;
            return this;
        }

        public Builder withName(final String val) {
            this.name = val;
            return this;
        }

        public Builder withLastName(final String val) {
            this.lastName = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
