package com.codecool.secureerp.dao;

import com.codecool.secureerp.model.HrModel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HrDao extends Dao<HrModel> {
    private final static int ID_TABLE_INDEX = 0;
    private final static int NAME_TABLE_INDEX = 1;
    private final static int BIRTH_DATE_TABLE_INDEX = 2;
    private final static int DEPARTMENT_TABLE_INDEX = 3;
    private final static int CLEARANCE_TABLE_INDEX = 4;
    private final static String DATA_FILE = "src/main/resources/hr.csv";
    public static String[] DEFAULT_HEADERS = {"Id", "Name", "Date of birth", "Department", "Clearance"};

    public HrDao() throws IOException{
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
        csvArray[ID_TABLE_INDEX] = customer.id();
        csvArray[NAME_TABLE_INDEX] = customer.name();
        csvArray[BIRTH_DATE_TABLE_INDEX] = customer.birthDate();
        csvArray[DEPARTMENT_TABLE_INDEX] = customer.department();
        csvArray[CLEARANCE_TABLE_INDEX] = Integer.toString(customer.clearance());
        return csvArray;
    }

    @Override
    public List<HrModel> loadData() throws IOException {
        return super.loadData(DATA_FILE);
    }

    @Override
    public void save(List<HrModel> data) throws IOException {
        super.save(DATA_FILE, data);
    }


    public String getYoungestEmployeeName() throws IOException {
        List<HrModel> data = loadData();
        HrModel youngestEmployee = data.get(0);
        for (HrModel hrEmployee : data) {
            String currentEmployeeBirthDate = hrEmployee.birthDate();
            if(youngestEmployee.birthDate().compareTo(currentEmployeeBirthDate) < 0) {
                youngestEmployee = hrEmployee;
            }
        }
        return youngestEmployee.name();
    }

    public String getOldestEmployeeName() throws IOException {
        List<HrModel> data = loadData();

        HrModel oldestEmployee = data.get(0);
        for (HrModel hrEmployee : data) {
            String currentEmployeeBirthDate = hrEmployee.birthDate();
            if(oldestEmployee.birthDate().compareTo(currentEmployeeBirthDate) > 0) {
                oldestEmployee = hrEmployee;
            }
        }
        return oldestEmployee.name();
    }

    public int getAverageAgeOfEmployees() throws IOException {

        List<HrModel> data = loadData();
        int agesSum = 0;
        int length = data.size();
        int currentYear = Year.now().getValue();

        for (HrModel hrEmployee : data) {
            int employeeBirthYear = Integer.parseInt(hrEmployee.birthDate().split("-")[0]);
            agesSum += currentYear - employeeBirthYear;
        }

        return agesSum / length;
    }

    public List<String> getEmployeesWithBirthdaysWithinTwoWeeks(String inputDate) throws IOException {
        List<HrModel> data = loadData();

        List<String> employeesWithBirthdaysWithinTwoWeeks = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = startDate.plusDays(14);

        for(HrModel hrEmployee : data) {
            LocalDate employeeBirthDate = LocalDate.parse(hrEmployee.birthDate());
            if (employeeBirthDate.isAfter(startDate.minusDays(1)) && employeeBirthDate.isBefore(endDate.plusDays(1))) {
                employeesWithBirthdaysWithinTwoWeeks.add(hrEmployee.name());
            }
        }

        return employeesWithBirthdaysWithinTwoWeeks;
    }

    public int getEmployeesWithMinimumClearanceLevel(int minimumClearanceLevel) throws IOException {
        List<HrModel> data = loadData();

        int employeesWithMinimumClearanceLevel = 0;

        for (HrModel hrEmployee : data) {
            if(hrEmployee.clearance() >= minimumClearanceLevel) {
                employeesWithMinimumClearanceLevel++;
            }
        }

        return employeesWithMinimumClearanceLevel;
    }

    public Map<String, Integer> getEmployeesCountByDepartment() throws IOException {
        List<HrModel> data = loadData();

        Map<String, Integer> departmentCountMap = new HashMap<>();

        for (HrModel hrEmployee : data) {
            String department = hrEmployee.department();
            departmentCountMap.put(department, departmentCountMap.getOrDefault(department, 0) + 1);
        }

        return departmentCountMap;
    }
}
