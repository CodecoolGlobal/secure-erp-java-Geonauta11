package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.HRModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HRDAO {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int BIRTH_DATE_TABLE_INDEX = 2;
    private final static int DEPARTMENT_TABLE_INDEX = 3;
    private final static int CLEARANCE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/hr.csv";
    public static String[] headers = {"Id", "Name", "Date of birth", "Department", "Clearance"};

    private List<HRModel> hrEmployees;

    private static String[] csvRowToArray(String row) {
        return row.split(";");
    }
    private static HRModel arrayToCustomer(String[] array) {
        int id = Integer.parseInt(array[ID_TABLE_INDEX]);
        String name = array[NAME_TABLE_INDEX];
        String birthDate = array[BIRTH_DATE_TABLE_INDEX];
        String department = array[DEPARTMENT_TABLE_INDEX];
        int clearance = Integer.parseInt(array[CLEARANCE_TABLE_INDEX]);
        return new HRModel(id, name, birthDate, department, clearance);
    }
    private static HRModel csvRowToCustomer(String row) {
        return arrayToCustomer(csvRowToArray(row));
    }
    private static String[] customerToArray(HRModel customer) {
        String[] csvArray = new String[5];
        csvArray[ID_TABLE_INDEX] = Integer.toString(customer.getId());
        csvArray[NAME_TABLE_INDEX] = customer.getName();
        csvArray[BIRTH_DATE_TABLE_INDEX] = customer.getBirthDate();
        csvArray[DEPARTMENT_TABLE_INDEX] = customer.getDepartment();
        csvArray[CLEARANCE_TABLE_INDEX] = Integer.toString(customer.getClearance());
        return csvArray;
    }
    private static String arrayToCsvRow(String[] array) {
        return String.join(";", array);
    }
    private static String customerToCsvRow(HRModel customer) {
        return arrayToCsvRow(customerToArray(customer));
    }

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
        hrEmployees = reader.lines()
                .map(HRDAO::csvRowToCustomer)
                .toList();
    }

    private String getDataAsCsv() {
        return hrEmployees.stream().map(HRDAO::customerToCsvRow)
                .collect(Collectors.joining("\n"));
    }

    public String[][] getDataAsTable() {
        return hrEmployees.stream().map(HRDAO::customerToArray).toArray(String[][]::new);
    }
    public void save() throws IOException {
        try (FileWriter fileWriter = new FileWriter(DATA_FILE)) {
            fileWriter.write(getDataAsCsv());
        }
    }

    // Return the name of the youngest employee
    public String getYoungestEmployeeName() {
        HRModel youngestEmployee = hrEmployees.get(0);
        for (HRModel hrEmployee : hrEmployees) {
            String currentEmployeeBirthDate = hrEmployee.getBirthDate();
            if(youngestEmployee.getBirthDate().compareTo(currentEmployeeBirthDate) > 0) {
                youngestEmployee = hrEmployee;
            }
        }
        return youngestEmployee.getName();
    }

    // Return the name of the oldest employee
    public String getOldestEmployeeName() {
        HRModel oldestEmployee = hrEmployees.get(0);
        for (HRModel hrEmployee : hrEmployees) {
            String currentEmployeeBirthDate = hrEmployee.getBirthDate();
            if(oldestEmployee.getBirthDate().compareTo(currentEmployeeBirthDate) < 0) {
                oldestEmployee = hrEmployee;
            }
        }
        return oldestEmployee.getName();
    }

    // Return the average age of HR employees
    public int getAverageAgeOfEmployees() {
        int agesSum = 0;
        int length = hrEmployees.size();
        int currentYear = Year.now().getValue();

        for (HRModel hrEmployee : hrEmployees) {
            int employeeBirthYear = Integer.parseInt(hrEmployee.getBirthDate().split("-")[0]);
            agesSum += currentYear - employeeBirthYear;
        }

        return agesSum / length;
    }

    // Return the name of employees who have birthdays within two weeks from the input date
    public List<String> getEmployeesWithBirthdaysWithinTwoWeeks(String inputDate) {
        List<String> employeesWithBirthdaysWithinTwoWeeks = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = startDate.plusDays(14);

        for(HRModel hrEmployee : hrEmployees) {
            LocalDate employeeBirthDate = LocalDate.parse(hrEmployee.getBirthDate());
            if (employeeBirthDate.isAfter(startDate.minusDays(1)) && employeeBirthDate.isBefore(endDate.plusDays(1))) {
                employeesWithBirthdaysWithinTwoWeeks.add(hrEmployee.getName());
            }
        }

        return employeesWithBirthdaysWithinTwoWeeks;
    }

    // Return the number of employees who have at least the input clearance level
    public int getEmployeesWithMinimumClearanceLevel(int minimumClearanceLevel) {
        int employeesWithMinimumClearanceLevel = 0;

        for (HRModel hrEmployee : hrEmployees) {
            if(hrEmployee.getClearance() >= minimumClearanceLevel) {
                employeesWithMinimumClearanceLevel++;
            }
        }

        return employeesWithMinimumClearanceLevel;
    }

    // Return the number of employees per department in a Map
    public Map<String, Integer> getEmployeesCountByDepartment() {
        Map<String, Integer> departmentCountMap = new HashMap<>();

        for (HRModel hrEmployee : hrEmployees) {
            String department = hrEmployee.getDepartment();
            departmentCountMap.put(department, departmentCountMap.getOrDefault(department, 0) + 1);
        }

        return departmentCountMap;
    }
}
