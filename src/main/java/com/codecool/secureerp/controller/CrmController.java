package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.CrmDao;
import com.codecool.secureerp.model.CrmModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

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
        boolean isRunning = true;
        while (isRunning) {
            terminalView.printMenu("Customer Relationship", OPTIONS);
            int selectedMenu = Integer.parseInt(terminalView.getInput("Please select one of the following options:"));
            isRunning = invokeMenuItem(selectedMenu);
        }

    }
    private boolean invokeMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 0 -> { return false; }
            case 1 -> printCustomers();
            case 2 -> addCustomer();
            case 3 -> updateCustomerById();
            case 4 -> deleteCustomer();
            case 5 -> printSubscribed();
            default -> terminalView.printErrorMessage("Invalid menu item selected!\n");
        }
        return true;
    }
    private void printCustomers() {
        terminalView.printTable(dao.getDataAsTable());
    }
    private void addCustomer() {
        String id = promptId(IdSearchType.NEW);
        CrmModel newCustomer = createCustomerFromInput(id);
        dao.addCustomer(newCustomer);
    }
    private void updateCustomerById() {
        String id = promptId(IdSearchType.EXISTING);
        CrmModel newCustomer = createCustomerFromInput(id);
        dao.updateCustomerById(id, newCustomer);
    }
    private String promptId(IdSearchType searchType) {
        String id;
        while (true) {
            id = terminalView.getInput("Id: ");
            boolean hasId = dao.hasId(id);
            switch (searchType) {
                case EXISTING -> {
                    if (hasId) return id;
                }
                case NEW -> {
                    if (!hasId) return id;
                }
                case ANY -> { return id;
                }
            }
            terminalView.printErrorMessage("id not present");
        }
    }
    private CrmModel createCustomerFromInput(String id) {
        String name = terminalView.getInput("Name: ");
        String email = terminalView.getInput("Email: ");
        boolean isSubscribed = promptIsSubscribed();
        return new CrmModel(id, name, email, isSubscribed);
    }
    private boolean promptIsSubscribed() {
        String isSubscribedString;
        do  {
            isSubscribedString = terminalView.getInput("Subscribed (Yes/No): ");
            if (isSubscribedString.equalsIgnoreCase("yes") ||
                isSubscribedString.equalsIgnoreCase("y")) {
                return true;
            } else if (isSubscribedString.equalsIgnoreCase("no") ||
                isSubscribedString.equalsIgnoreCase("n")) {
                return false;
            }
            terminalView.printMessage("invalid input");
        } while (true);
    }
    private void deleteCustomer() {
        String id = promptId(IdSearchType.EXISTING);
        dao.deleteCustomerById(id);
    }
    private void printSubscribed() {
        List<CrmModel> subscribedList = dao.getData().stream().filter(CrmModel::isSubscribed).toList();
        terminalView.printGeneralResults(subscribedList.toString(), "subscribed");
    }

}
