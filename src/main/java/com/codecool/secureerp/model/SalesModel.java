package com.codecool.secureerp.model;


public class SalesModel {
    private final String id;
    private final String customerId;
    private final String product;
    private final int price;
    private final String transactionDate;


    public SalesModel(String id, String customerId, String product, int price, String date) {
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

    public int getPrice() {
        return price;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
}
