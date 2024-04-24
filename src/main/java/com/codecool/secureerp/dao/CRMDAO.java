package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.CRMModel;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CRMDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};

    private List<CRMModel> data;

    private static String[] csvRowToArray(String row) {
        return row.split(";");
    }
    private static CRMModel arrayToCustomer(String[] array) {
        String id = array[ID_TABLE_INDEX];
        String name = array[NAME_TABLE_INDEX];
        String email = array[EMAIL_TABLE_INDEX];
        boolean isSubscribed = array[SUBSCRIBED_TABLE_INDEX].equals("1");
        return new CRMModel(id, name, email, isSubscribed);
    }
    private static CRMModel csvRowToCustomer(String row) {
        return arrayToCustomer(csvRowToArray(row));
    }
    private static String[] customerToArray(CRMModel customer) {
        String[] csvArray = new String[4];
        csvArray[ID_TABLE_INDEX] = customer.getId();
        csvArray[NAME_TABLE_INDEX] = customer.getName();
        csvArray[EMAIL_TABLE_INDEX] = customer.getEmail();
        csvArray[SUBSCRIBED_TABLE_INDEX] = customer.isSubscribed() ? "1" : "0";
        return csvArray;
    }
    private static String arrayToCsvRow(String[] array) {
        return String.join(";", array);
    }
    private static String customerToCsvRow(CRMModel customer) {
        return arrayToCsvRow(customerToArray(customer));
    }

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        data = reader.lines()
            .map(CRMDAO::csvRowToCustomer)
            .toList();
    }

    private String getDataAsCsv() {
        return data.stream().map(CRMDAO::customerToCsvRow)
            .collect(Collectors.joining("\n"));
    }

    public String[][] getDataAsTable() {
        return data.stream().map(CRMDAO::customerToArray).toArray(String[][]::new);
    }
    public void save() throws IOException {
        try (FileWriter fileWriter = new FileWriter(DATA_FILE)) {
            fileWriter.write(getDataAsCsv());
        }

    }
}
