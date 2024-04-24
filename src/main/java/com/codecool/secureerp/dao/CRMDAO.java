package com.codecool.secureerp.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CRMDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int EMAIL_TABLE_INDEX = 2;
    private final static int SUBSCRIBED_TABLE_INDEX = 3;
    private final static String DATA_FILE = "src/main/resources/crm.csv";
    public static String[] headers = {"Id", "Name", "Email", "Subscribed"};

    public String[][] read () throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        Stream<String> streamOfFileByLines = reader.lines();


        List<String> arrayOfStrings = streamOfFileByLines.toList();
        reader.close();
        List<String[]> matrix = new ArrayList<String[]>();
        for (int i = 0; i < arrayOfStrings.size(); i++){
            matrix.add(arrayOfStrings.get(i).split(";"));
        }
        return matrix.toArray(new String[matrix.size()][]);
    }

    public void write (String newLine) throws IOException {
        Path path = Paths.get(DATA_FILE);
        Files.write(path, newLine.getBytes());

    }
}
