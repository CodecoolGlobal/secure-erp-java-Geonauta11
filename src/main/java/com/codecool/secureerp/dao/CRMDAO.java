package com.codecool.secureerp.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CRMDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};
    private List<String[]> data = new ArrayList<>();

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        data = reader.lines()
            .map((s) -> s.split(";"))
            .toList();
    }
    public String[][] getDataAsTable() {
        return data.toArray(new String[0][]);
    }

    private String getDataAsCsv() {
        return data.stream().map((row) -> String.join(";", row))
            .collect(Collectors.joining("\n"));
    }

    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter(DATA_FILE);
        fileWriter.write(getDataAsCsv());

    }
}
