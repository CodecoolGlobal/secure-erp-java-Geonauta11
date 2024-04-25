package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.HrDao;
import com.codecool.secureerp.model.HrModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;

public class HrController {
    public static final String[] OPTIONS = new String[]{
            "Back to main menu",
            "List employees",
            "Add new employee",
            "Update employee",
            "Remove employee",
            "Oldest and youngest employees",
            "Employees average age",
            "Employees with birthdays in the next two weeks",
            "Employees with clearance level",
            "Employee numbers by department"
    };
    private final TerminalView terminalView;
    private final HrDao dao;

    public HrController(TerminalView terminalView) throws IOException {
        this.terminalView = terminalView;
        dao = new HrDao();
        dao.loadData();
    }

    public void menu() {
        boolean isRunning = true;
        while (isRunning) {
            terminalView.printMenu("Human Resources", OPTIONS);
            int selectedMenu = Integer.parseInt(terminalView.getInput("Please select one of the following options:"));
            isRunning = invokeMenuItem(selectedMenu);
        }
    }

    private boolean invokeMenuItem(int selectedMenu) {
        try {
            switch (selectedMenu) {
                case 0 -> { return false; }
                case 1 -> listEmployees();
                case 2 -> addEmployee();
                case 3 -> updateEmployeeById();
                case 4 -> deleteEmployee();
                case 5 -> printOldestAndYoungestEmployee();
                case 6 -> printEmployeesAverageAge();
                case 7 -> printEmployeesWithBirthdaysWithinTwoWeeks();
                case 8 -> printEmployeesWithClearanceLevel();
                case 9 -> printEmployeesByDepartment();
                default -> terminalView.printErrorMessage("Invalid menu item selected!\n");
            }
        } catch (IOException e) {
            terminalView.printErrorMessage(e.getMessage());
        }
        return true;
    }


    private void listEmployees() throws IOException {
        terminalView.printTable(dao.getDataAsTable());
    }

    private void addEmployee() throws IOException {
        HrModel newEmployee = promptUser();
        dao.addModel(newEmployee);
    }

    private void updateEmployeeById() throws IOException {
        HrModel updatedEmployee = promptUser();
        dao.updateModelById(updatedEmployee.id(), updatedEmployee);
    }

    private void deleteEmployee() throws IOException {
        String id = terminalView.getInput("Enter employee id: ");
        dao.deleteModelById(id);
    }

    private void printOldestAndYoungestEmployee() throws IOException {
        String youngestEmployeeName = dao.getYoungestEmployeeName();
        String oldestEmployeeName = dao.getOldestEmployeeName();

        terminalView.printGeneralResults(youngestEmployeeName, "youngest");
        terminalView.printGeneralResults(oldestEmployeeName, "oldest");
    }

    private void printEmployeesAverageAge() throws IOException {
        terminalView.printGeneralResults(Integer.toString(dao.getAverageAgeOfEmployees()), "average age");
    }

    private void printEmployeesWithBirthdaysWithinTwoWeeks() throws IOException {
        String date = terminalView.getInput("Enter a date (yyyy-mm-dd): ");
        terminalView.printGeneralResults(dao.getEmployeesWithBirthdaysWithinTwoWeeks(date).toString(), "employees with birthdays within 2 weeks");
    }

    private void printEmployeesWithClearanceLevel() throws IOException {
        int clearanceLevel = Integer.parseInt(terminalView.getInput("Enter a clearance level: "));
        terminalView.printGeneralResults(Integer.toString(dao.getEmployeesWithMinimumClearanceLevel(clearanceLevel)), "clearance level");
    }

    private void printEmployeesByDepartment() throws IOException {
        terminalView.printMessage(dao.getEmployeesCountByDepartment().toString());
    }

    private HrModel promptUser() {
        String id = terminalView.getInput("Enter ID:");
        String name = terminalView.getInput("Enter name:");
        String birthDate = terminalView.getInput("Enter birth date:");
        String department = terminalView.getInput("Enter department:");
        int clearance = Integer.parseInt(terminalView.getInput("Enter clearance:"));

        return new HrModel(id, name, birthDate, department, clearance);
    }
}
