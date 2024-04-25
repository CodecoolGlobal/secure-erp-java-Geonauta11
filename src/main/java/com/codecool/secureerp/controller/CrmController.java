package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.CrmDao;
import com.codecool.secureerp.view.TerminalView;

import java.io.Closeable;
import java.io.IOException;

public class CrmController implements Closeable {
    public static final String[] OPTIONS = new String[]{
            "Back to main menu",
            "List customers",
            "Add new customer",
            "Update customer",
            "Remove customer",
            "Subscribed customer emails"
    };
    private final TerminalView terminalView;
    private final CrmDao dao;

    public CrmController(TerminalView terminalView) throws IOException{
        this.terminalView = terminalView;
        dao = new CrmDao();
        dao.load();
    }
    public void close() throws IOException{
        dao.save();
    }

    public void displayMenu() {
        terminalView.printMenu("Customer Relationship", OPTIONS);
        terminalView.getInput("Please select one of the following options:");
        terminalView.printTable(dao.getDataAsTable());
    }
}
