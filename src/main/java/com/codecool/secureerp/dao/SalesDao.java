package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.SalesModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesDao {

    private final static int ID_TABLE_INDEX = 0;
    private final static int CUSTOMER_ID_TABLE_INDEX = 1;
    private final static int PRODUCT_TABLE_INDEX = 2;
    private final static int PRICE_TABLE_INDEX = 3;
    private final static int TRANSACTION_DATE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/sales.csv";
    public static String[] headers = {"Id", "Customer Id", "Product", "Price", "Transaction Date"};

    private List<SalesModel> data;

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        data = reader.lines()
                .map(SalesDao::csvRowToCustomer)
                .toList();
    }
    private static String[] csvRowToArray(String row) {
        return row.split(";");
    }


    private static SalesModel csvRowToCustomer(String row) {
        return arrayToSale(csvRowToArray(row));
    }


    private static SalesModel arrayToSale(String[] array) {
        String id = array[ID_TABLE_INDEX];
        String customerId = array[CUSTOMER_ID_TABLE_INDEX];
        String product = array[PRODUCT_TABLE_INDEX];
        int price = Integer.parseInt(array[PRICE_TABLE_INDEX]);
        String date = array[TRANSACTION_DATE_TABLE_INDEX];
        return new SalesModel(id, customerId, product, price, date);
    }

    private static String[] saleToArray(SalesModel sale) {
        String[] salesArray = new String[5];
        salesArray[ID_TABLE_INDEX] = sale.getId();
        salesArray[CUSTOMER_ID_TABLE_INDEX] = sale.getCustomerId();
        salesArray[PRODUCT_TABLE_INDEX] = sale.getProductName();
        salesArray[PRICE_TABLE_INDEX] = sale.getPrice() + "";
        salesArray[TRANSACTION_DATE_TABLE_INDEX] = sale.getTransactionDate();

        return salesArray;
    }

    public SalesModel getSaleWithBiggestRevenue (List<SalesModel> sales){
        SalesModel saleWithBiggestRevenue = sales.get(0);
        for (SalesModel sale : sales) {
            if (sale.getPrice() > saleWithBiggestRevenue.getPrice()){
                saleWithBiggestRevenue = sale;
            }
        }
        return saleWithBiggestRevenue;
    }

    public String getBiggestRevenueProduct (List<SalesModel> sales){
        List<String> productRecords = getRecords(sales);
        String biggestRevenueProduct = productRecords.get(0);
        int biggestRevenue = 0;

        for (String record : productRecords) {
            int currentRevenue = 0;
            for (SalesModel sale : sales) {
                if (sale.getProductName().equals(record)) {
                    currentRevenue += sale.getPrice();
                }
            }
            if (currentRevenue > biggestRevenue) {
                biggestRevenue = currentRevenue;
                biggestRevenueProduct = record;
            }
        }
        return biggestRevenueProduct;
    }

    private List<String> getRecords (List<SalesModel> sales){
        List<String> productRecords = new ArrayList<>();
        for (SalesModel sale : sales) {
            if (!productRecords.contains(sale.getProductName())){
                productRecords.add(sale.getProductName());
            }
        }
        return productRecords;
    }

}
