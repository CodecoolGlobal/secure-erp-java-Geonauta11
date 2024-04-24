package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.CRMDAO;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;

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
    private final CRMDAO dao;

    public CRMController(TerminalView terminalView) throws IOException{
        this.terminalView = terminalView;
        dao = new CRMDAO();
        dao.load();

    }
    public void tearDown() throws IOException{
        dao.save();
    }

    public void displayMenu() {
        terminalView.printMenu("Customer Relationship", OPTIONS);
        terminalView.getInput("Please select one of the following options:");
        terminalView.printTable(dao.getDataAsTable());
    }
}
