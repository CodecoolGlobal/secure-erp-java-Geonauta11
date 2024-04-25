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

    public void menu() {
        terminalView.printMenu("Sales", OPTIONS);
        int selected = Integer.parseInt(terminalView.getInput("Pick an option: "));
    }
}
