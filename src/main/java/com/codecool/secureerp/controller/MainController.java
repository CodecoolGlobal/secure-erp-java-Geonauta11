package com.codecool.secureerp.controller;

import com.codecool.secureerp.view.TerminalView;

import java.io.Closeable;
import java.io.IOException;

public class MainController{
    public static final String[] OPTIONS = new String[]{
            "Exit program",
            "Customer Relationship Management (CRM)",
            "Sales",
            "Human Resources"
    };
    private final CrmController crmController;
    private final SalesController salesController;
    private final HrController hrController;
    private final TerminalView terminalView;

    public MainController(CrmController crmController, SalesController salesController, HrController hrController, TerminalView terminalView) {
        this.crmController = crmController;
        this.salesController = salesController;
        this.hrController = hrController;
        this.terminalView = terminalView;
    }

    public void menu() {
        boolean isRunning = true;
        while (isRunning) {
            terminalView.printMenu("Main menu", OPTIONS);
            try {
                int menuItemIndex = Integer.parseInt(terminalView.getInput("Please select a menu item!"));
                isRunning = invokeMenuItem(menuItemIndex);
            } catch( NumberFormatException e) {
                terminalView.printErrorMessage("not a number");
            }
        }
    }

    private boolean invokeMenuItem(int selectedMenu) {
        try {
            switch (selectedMenu) {
                case 0 -> {
                    return false;
                }
                case 1 -> crmController.menu();
                case 2 -> salesController.menu(crmController.getAllCustomerId());
                case 3 -> hrController.menu();
                default -> terminalView.printErrorMessage("Invalid menu item selected!\n");
            }
        } catch (IOException e) {

        }
        return true;
    }
}
