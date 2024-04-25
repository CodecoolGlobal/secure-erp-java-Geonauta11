package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.HrModel;
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

public class HrDao extends Dao<HrModel> {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int BIRTH_DATE_TABLE_INDEX = 2;
    private final static int DEPARTMENT_TABLE_INDEX = 3;
    private final static int CLEARANCE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/hr.csv";
    public static String[] DEFAULT_HEADERS = {"Id", "Name", "Date of birth", "Department", "Clearance"};

    public HrDao() {
        super(DEFAULT_HEADERS);
    }

    @Override
    protected HrModel arrayToModel(String[] array) {
        String id = array[ID_TABLE_INDEX];
        String name = array[NAME_TABLE_INDEX];
        String birthDate = array[BIRTH_DATE_TABLE_INDEX];
        String department = array[DEPARTMENT_TABLE_INDEX];
        int clearance = Integer.parseInt(array[CLEARANCE_TABLE_INDEX]);
        return new HrModel(id, name, birthDate, department, clearance);
    }

    @Override
    protected String[] modelToArray(HrModel customer) {
        String[] csvArray = new String[5];
        csvArray[ID_TABLE_INDEX] = customer.getId();
        csvArray[NAME_TABLE_INDEX] = customer.getName();
        csvArray[BIRTH_DATE_TABLE_INDEX] = customer.getBirthDate();
        csvArray[DEPARTMENT_TABLE_INDEX] = customer.getDepartment();
        csvArray[CLEARANCE_TABLE_INDEX] = Integer.toString(customer.getClearance());
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

    public void addEmployee(HrModel employee) {
        data.add(employee);
    }

    public boolean updateEmployee(HrModel employee) {
        int index = findEmployeeIndexById(employee.getId());
        if (index == -1) {
            return false;
        }
        data.set(index, employee);
        return true;
    }

    private int findEmployeeIndexById(String id) {
        for(int i = 0; i < data.size(); i++) {
            HrModel employee = data.get(i);
            if(id.equals(employee.getId())) {
                return i;
            }
        }
        return -1;
    }

    public boolean deleteEmployeeById(String id) {
         int index = findEmployeeIndexById(id);
         if(index == -1) {
             return false;
         }
         data.remove(index);
         return true;
    }

    public String getYoungestEmployeeName() {
        HrModel youngestEmployee = data.get(0);
        for (HrModel hrEmployee : data) {
            String currentEmployeeBirthDate = hrEmployee.getBirthDate();
            if(youngestEmployee.getBirthDate().compareTo(currentEmployeeBirthDate) < 0) {
                youngestEmployee = hrEmployee;
            }
        }
        return youngestEmployee.getName();
    }

    public String getOldestEmployeeName() {
        HrModel oldestEmployee = data.get(0);
        for (HrModel hrEmployee : data) {
            String currentEmployeeBirthDate = hrEmployee.getBirthDate();
            if(oldestEmployee.getBirthDate().compareTo(currentEmployeeBirthDate) > 0) {
                oldestEmployee = hrEmployee;
            }
        }
        return oldestEmployee.getName();
    }

    public int getAverageAgeOfEmployees() {
        int agesSum = 0;
        int length = data.size();
        int currentYear = Year.now().getValue();

        for (HrModel hrEmployee : data) {
            int employeeBirthYear = Integer.parseInt(hrEmployee.getBirthDate().split("-")[0]);
            agesSum += currentYear - employeeBirthYear;
        }

        return agesSum / length;
    }

    public List<String> getEmployeesWithBirthdaysWithinTwoWeeks(String inputDate) {
        List<String> employeesWithBirthdaysWithinTwoWeeks = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = startDate.plusDays(14);

        for(HrModel hrEmployee : data) {
            LocalDate employeeBirthDate = LocalDate.parse(hrEmployee.getBirthDate());
            if (employeeBirthDate.isAfter(startDate.minusDays(1)) && employeeBirthDate.isBefore(endDate.plusDays(1))) {
                employeesWithBirthdaysWithinTwoWeeks.add(hrEmployee.getName());
            }
        }

        return employeesWithBirthdaysWithinTwoWeeks;
    }

    public int getEmployeesWithMinimumClearanceLevel(int minimumClearanceLevel) {
        int employeesWithMinimumClearanceLevel = 0;

        for (HrModel hrEmployee : data) {
            if(hrEmployee.getClearance() >= minimumClearanceLevel) {
                employeesWithMinimumClearanceLevel++;
            }
        }

        return employeesWithMinimumClearanceLevel;
    }

    public Map<String, Integer> getEmployeesCountByDepartment() {
        Map<String, Integer> departmentCountMap = new HashMap<>();

        for (HrModel hrEmployee : data) {
            String department = hrEmployee.getDepartment();
            departmentCountMap.put(department, departmentCountMap.getOrDefault(department, 0) + 1);
        }

        return departmentCountMap;
    }
}
