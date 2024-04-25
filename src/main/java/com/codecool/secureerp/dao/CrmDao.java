package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.CrmModel;

import java.io.*;

public class CrmDao extends Dao<CrmModel>{
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    private final static String[] DEFAULT_HEADERS = {"Id", "Name", "Email", "Subscribed"};

    public CrmDao() {
        super(DEFAULT_HEADERS);
    }

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
    @Override
    public void load() throws IOException {
        super.load(DATA_FILE);
    }
    @Override
    public void save() throws IOException {
        super.save(DATA_FILE);
    }
    public CrmModel getCustomerById(String id) {
        return data.stream()
            .filter((customer) -> id.equals(customer.id()))
            .findFirst()
            .orElse(null);
    }
    private int getCustomerIndexById(String id) {
        for(int i=0; i<data.size(); i++) {
            CrmModel customer = data.get(i);
            if (id.equals(customer.id())) {
                return i;
            }
        }
        return -1;
    }
    public boolean addCustomer(CrmModel customer) {
        data.add(customer);
        return true;

    }
    public boolean updateCustomerById(String id, CrmModel newCustomer) {
        int index = getCustomerIndexById(id);
        if (index == -1) return false;
        data.set(index, newCustomer);
        return true;
    }
    public boolean deleteCustomerById(String id) {
        int index = getCustomerIndexById(id);
        if (index == -1) return false;
        data.remove(index);
        return true;
    }
    public boolean hasId(String id) {
        return (getCustomerById(id) != null);
    }
}
