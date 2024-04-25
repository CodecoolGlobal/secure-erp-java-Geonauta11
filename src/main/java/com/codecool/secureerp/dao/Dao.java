package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Dao<T extends Model> {
    protected List<T> data;

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

    public String[][] getDataAsTable() {
        System.out.println(data.toString());
        String[][] table = data.stream().map(this::modelToArray).toArray(String[][]::new);
        System.out.println(Arrays.deepToString(table));

        return table;
    }
    public void load(String DATA_FILE) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        data = reader.lines()
            .map(this::csvRowToModel)
            .toList();
    }
    public void save(String DATA_FILE) throws IOException {
        try (FileWriter fileWriter = new FileWriter(DATA_FILE)) {
            fileWriter.write(getDataAsCsv());
        }

    }

    protected T csvRowToModel(String row) {
        return arrayToModel(csvRowToArray(row));
    }
    protected String[] csvRowToArray(String row) {
        return row.split(";");
    }
    protected abstract T arrayToModel(String[] array);
    protected abstract String[] modelToArray(T customer);
}
