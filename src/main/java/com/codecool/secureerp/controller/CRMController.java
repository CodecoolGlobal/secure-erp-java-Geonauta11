package com.codecool.secureerp.controller;

import com.codecool.secureerp.view.TerminalView;

public class CRMController {
    public static final String[] OPTIONS = new String[]{
            "Back to main menu",
            "List customers",
            "Add new customer",
            "Update customer",
            "Remove customer",
            "Subscribed customer emails"
    };
    private final TerminalView terminalView;

    public CRMController(TerminalView terminalView) {
        this.terminalView = terminalView;
    }

    public void displayMenu() {
        terminalView.printMenu("Customer Relationship", OPTIONS);
    }
}
