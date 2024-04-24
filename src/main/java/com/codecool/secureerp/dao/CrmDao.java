package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.CrmModel;
import com.codecool.secureerp.model.Model;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class CrmDao extends Dao<CrmModel>{
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};


    @Override
    protected CrmModel arrayToModel(String[] array) {
        String id = array[ID_TABLE_INDEX];
        String name = array[NAME_TABLE_INDEX];
        String email = array[EMAIL_TABLE_INDEX];
        boolean isSubscribed = array[SUBSCRIBED_TABLE_INDEX].equals("1");
        return new CrmModel(id, name, email, isSubscribed);
    }

    @Override
    protected String[] modelToArray(CrmModel customer) {
        String[] csvArray = new String[4];
        csvArray[ID_TABLE_INDEX] = customer.id();
        csvArray[NAME_TABLE_INDEX] = customer.name();
        csvArray[EMAIL_TABLE_INDEX] = customer.email();
        csvArray[SUBSCRIBED_TABLE_INDEX] = customer.isSubscribed() ? "1" : "0";
        return csvArray;
    }
    public void load() throws IOException {
        super.load(DATA_FILE);
    }
    public void save() throws IOException {
        super.save(DATA_FILE);
    }
}
