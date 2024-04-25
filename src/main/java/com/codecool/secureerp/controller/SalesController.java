package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.SalesDao;
import com.codecool.secureerp.view.TerminalView;

public class SalesController {
    public static final String[] OPTIONS = new String[]{
            "Back to main menu",
            "List transactions",
            "Add new transaction",
            "Update transaction",
            "Remove transaction",
            "Get the transaction that made the biggest revenue",
            "Get the product that made the biggest revenue altogether",
            "Count number of transactions between",
            "Sum the price of transactions between"
    };
    private final TerminalView terminalView;
    private final SalesDao dao = new SalesDao();

    public SalesController(TerminalView terminalView) {
        this.terminalView = terminalView;
    }

    public void displayMenu() {
        terminalView.printMenu("Sales", OPTIONS);
        int selected = Integer.parseInt(terminalView.getInput("Pick an option: "));
//        switch (selected) {
//            case 0 -> {
//                break;
//            }
//
//            case 1 -> {
//                terminalView.getInputs();
//                dao.displayMenu();
//            }
//            case 2 -> {
//                dao.displayMenu();
//            }
//            case 3 -> {
//                dao.displayMenu();
//            }
//            case 4 -> {
//                dao.displayMenu();
//            }
//            case 5 -> {
//                dao.displayMenu();
//            }
//            case 6 -> {
//                dao.displayMenu();
//            }
//            case 7 -> {
//                dao.displayMenu();
//            }
//            case 8 -> {
//                dao.displayMenu();
//            }
//            default -> terminalView.printErrorMessage("Invalid menu item selected!\n");
//        }
    }
}
