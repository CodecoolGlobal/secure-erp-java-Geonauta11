package com.codecool.secureerp.dao;

import com.codecool.secureerp.controller.CrmController;
import com.codecool.secureerp.model.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Dao<T extends Model> {
    protected List<T> data = new ArrayList<>();
    protected String[] headers;

    public Dao(String[] headers) {
        this.headers = headers;
    }

    protected String arrayToCsvRow(String[] array) {
        return String.join(";", array);
    }
    protected String modelToCsvRow(T customer) {
        return arrayToCsvRow(modelToArray(customer));
    }

    protected String getDataAsCsv() {
        return data.stream().map(this::modelToCsvRow)
            .collect(Collectors.joining("\n"));
    }

    protected String[] getHeaders() {
        return headers;
    }

    public String[][] getDataAsTable() {
        List<String[]> table = data.stream().map(this::modelToArray).collect(Collectors.toList());
        table.add(0, getHeaders());
        return table.toArray(new String[0][]);
    }
    public void load(String DATA_FILE) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        data = reader.lines()
            .map(this::csvRowToModel)
            .collect(Collectors.toList());
    }
    public void save(String DATA_FILE) throws IOException {
        try (FileWriter fileWriter = new FileWriter(DATA_FILE)) {
            fileWriter.write(getDataAsCsv());
        }
    }

    public List<T> getData() {
        return data;
    }

    protected T csvRowToModel(String row) {
        return arrayToModel(csvRowToArray(row));
    }
    protected String[] csvRowToArray(String row) {
        return row.split(";");
    }

    public abstract void load() throws IOException;
    public abstract void save() throws IOException;
    protected abstract T arrayToModel(String[] array);
    protected abstract String[] modelToArray(T customer);
}
