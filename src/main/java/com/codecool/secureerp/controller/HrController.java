package com.codecool.secureerp.controller;

import com.codecool.secureerp.view.TerminalView;

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

    public HrController(TerminalView terminalView) {
        this.terminalView = terminalView;
    }

    public void displayMenu() {
        terminalView.printMenu("Human Resources", OPTIONS);
    }
}
