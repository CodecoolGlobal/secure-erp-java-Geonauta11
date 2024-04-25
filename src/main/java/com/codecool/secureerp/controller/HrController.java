package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.HrDao;
import com.codecool.secureerp.model.CrmModel;
import com.codecool.secureerp.model.HrModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.util.List;

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
        HrModel updatedEmployee = promptUser();
        dao.updateEmployee(updatedEmployee);
    }

    private void deleteEmployee() {
        String id = terminalView.getInput("Enter employee id: ");
        dao.deleteEmployeeById(id);
    }

    private void printOldestAndYoungestEmployee() {
        String youngestEmployeeName = dao.getYoungestEmployeeName();
        String oldestEmployeeName = dao.getOldestEmployeeName();

        terminalView.printGeneralResults(youngestEmployeeName, "youngest");
        terminalView.printGeneralResults(oldestEmployeeName, "oldest");
    }

    private void printEmployeesAverageAge() {
        terminalView.printGeneralResults(Integer.toString(dao.getAverageAgeOfEmployees()), "average age");
    }

    private void printEmployeesWithBirthdaysWithinTwoWeeks() {
        String date = terminalView.getInput("Enter a date (yyyy-mm-dd): ");
        terminalView.printGeneralResults(dao.getEmployeesWithBirthdaysWithinTwoWeeks(date).toString(), "employees with birthdays within 2 weeks");
    }

    private void printEmployeesWithClearanceLevel() {
        int clearanceLevel = Integer.parseInt(terminalView.getInput("Enter a clearance level: "));
        terminalView.printGeneralResults(Integer.toString(dao.getEmployeesWithMinimumClearanceLevel(clearanceLevel)), "clearance level");
    }

    private void printEmployeesByDepartment() {
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
