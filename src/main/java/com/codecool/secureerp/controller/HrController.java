package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.HrDao;
import com.codecool.secureerp.model.CrmModel;
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
        dao.load();
    }

    public void close() throws IOException {
        dao.save();
    }

    public void displayMenu() {
        boolean isRunning = true;
        while (isRunning) {
            terminalView.printMenu("Human Resources", OPTIONS);
            int selectedMenu = Integer.parseInt(terminalView.getInput("Please select one of the following options:"));
            isRunning = invokeMenuItem(selectedMenu);
        }
    }

    private boolean invokeMenuItem(int selectedMenu) {
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
        return true;
    }


    private void listEmployees() {
        terminalView.printTable(dao.getDataAsTable());
    }

    private void addEmployee() {
        HrModel newEmployee = promptUser();
        dao.addEmployee(newEmployee);
    }

    private void updateEmployeeById() {

    }

    private void deleteEmployee() {

    }

    private void printOldestAndYoungestEmployee() {

    }

    private void printEmployeesAverageAge() {

    }

    private void printEmployeesWithBirthdaysWithinTwoWeeks() {

    }

    private void printEmployeesWithClearanceLevel() {

    }

    private void printEmployeesByDepartment() {

    }

    // Prompt User
    private HrModel promptUser() {
        int id = Integer.parseInt(terminalView.getInput("Enter ID:"));
        String name = terminalView.getInput("Enter name:");
        String birthDate = terminalView.getInput("Enter birth date:");
        String department = terminalView.getInput("Enter department:");
        int clearance = Integer.parseInt(terminalView.getInput("Enter clearance:"));

        return new HrModel(id, name, birthDate, department, clearance);
    }
}
