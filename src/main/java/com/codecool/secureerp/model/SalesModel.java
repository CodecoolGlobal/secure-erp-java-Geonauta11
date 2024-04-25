package com.codecool.secureerp.model;


public class SalesModel implements Model {
    private final String id;
    private final String customerId;
    private final String product;
    private final double price;
    private final String transactionDate;


    public SalesModel(String id, String customerId, String product, double price, String date) {
        this.id = id;
        this.customerId = customerId;
        this.product = product;
        this.price = price;
        this.transactionDate = date;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductName() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString(){
    return "Id: " + id + "| Customer Id: " + customerId + "| Product: " + product + "| Price: " + price + "| Transaction Date: " + transactionDate;
    }
}
