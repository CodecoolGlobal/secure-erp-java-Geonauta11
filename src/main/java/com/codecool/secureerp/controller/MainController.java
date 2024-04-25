package com.codecool.secureerp.controller;

import com.codecool.secureerp.view.TerminalView;

import java.io.Closeable;
import java.io.IOException;

public class MainController implements Closeable {
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
            int menuItemIndex = Integer.parseInt(terminalView.getInput("Please select a menu item!"));
            isRunning = invokeMenuItem(menuItemIndex);
        }
    }

    private boolean invokeMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 0 -> {
                return false;
            }
            case 1 -> crmController.displayMenu();
            case 2 -> salesController.displayMenu();
            case 3 -> hrController.displayMenu();
            default -> terminalView.printErrorMessage("Invalid menu item selected!\n");
        }
        return true;
    }

    @Override
    public void close() {
        try {
            crmController.close();
        } catch (IOException ignored) {}
//        try {
//            hrController.close();
//        } catch(IOException ignored) {}
//        try {
//            salesController.close();
//        } catch(IOException ignored) {}
    }
}
