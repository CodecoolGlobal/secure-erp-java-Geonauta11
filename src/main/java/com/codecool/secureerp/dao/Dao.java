package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Dao<T extends Model> {
    private final String[] headers;

    public Dao(String[] headers) throws IOException {
        this.headers = headers;
    }

    protected String arrayToCsvRow(String[] array) {
        return String.join(";", array);
    }
    protected String modelToCsvRow(T customer) {
        return arrayToCsvRow(modelToArray(customer));
    }

    protected String convertToCsv(List<T> data) throws IOException {
        return data.stream().map(this::modelToCsvRow)
            .collect(Collectors.joining("\n"));
    }


    protected String[] getHeaders() {
        return headers;
    }

    public String[][] getDataAsTable() throws IOException {
        List<String[]> table = loadData().stream().map(this::modelToArray).collect(Collectors.toList());
        table.add(0, getHeaders());
        return table.toArray(new String[0][]);
    }
    public List<T> loadData(String DATA_FILE) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        return reader.lines()
            .map(this::csvRowToModel)
            .collect(Collectors.toList());
    }
    public void save(String DATA_FILE, List<T> data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(DATA_FILE)) {
            fileWriter.write(convertToCsv(data));
        }
        System.out.println("save successful");
    }

    protected T csvRowToModel(String row) {
        return arrayToModel(csvRowToArray(row));
    }
    protected String[] csvRowToArray(String row) {
        return row.split(";");
    }
    public T getModelById(String id) throws IOException {
        return loadData().stream()
            .filter((customer) -> id.equals(customer.id()))
            .findFirst()
            .orElse(null);
    }
    private int getModelIndexById(String id) throws IOException {
        List<T> data = loadData();
        for(int i=0; i<data.size(); i++) {
            T customer = data.get(i);
            if (id.equals(customer.id())) {
                return i;
            }
        }
        return -1;
    }
    public boolean addModel(T model) throws IOException {
        List<T> data = loadData();
        data.add(model);
        save(data);
        return true;

    }
    public boolean updateModelById(String id, T newModel) throws IOException {
        List<T> data = loadData();
        int index = getModelIndexById(id);
        if (index == -1) return false;
        data.set(index, newModel);
        save(data);
        return true;
    }
    public boolean deleteModelById(String id) throws IOException {
        List<T> data = loadData();
        int index = getModelIndexById(id);
        if (index == -1) return false;
        data.remove(index);
        save(data);
        return true;
    }
    public boolean hasId(String id) throws IOException {
        return (getModelById(id) != null);
    }

    public abstract List<T> loadData() throws IOException;
    public abstract void save(List<T> data) throws IOException;
    protected abstract T arrayToModel(String[] array);
    protected abstract String[] modelToArray(T customer);
}
