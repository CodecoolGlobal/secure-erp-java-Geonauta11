package com.codecool.secureerp.model;

public record SalesModel(String id, String customerId, String productName, double price, String transactionDate) implements Model{
}
