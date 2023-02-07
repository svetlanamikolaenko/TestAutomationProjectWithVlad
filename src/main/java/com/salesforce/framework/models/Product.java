package com.salesforce.framework.models;

public class Product {

    private int id;
    private String name;
    private String description;
    private double price;
    private int category_id;
    private String  category_name;

    public Product(){}

    public Product(String name, String description, double price, int category_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
    }

    public Product(int id, String name, String description, double price, int category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
    }

    public Product(int id, String name, String description, double price, int category_id, String category_name) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

}
