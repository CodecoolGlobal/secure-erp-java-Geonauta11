package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.CrmDao;
import com.codecool.secureerp.model.CrmModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.util.List;

public class CrmController{
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
    }

    public void displayMenu() {
        boolean isRunning = true;
        while (isRunning) {
            terminalView.printMenu("Customer Relationship", OPTIONS);
            try {
                int selectedMenu = Integer.parseInt(terminalView.getInput("Please select one of the following options:"));
                isRunning = invokeMenuItem(selectedMenu);
            } catch( NumberFormatException e) {
                terminalView.printErrorMessage("not a number");
            }
        }

    }
    private boolean invokeMenuItem(int selectedMenu) {
        try {
            switch (selectedMenu) {
                case 0 -> { return false; }
                case 1 -> printCustomers();
                case 2 -> addCustomer();
                case 3 -> updateCustomerById();
                case 4 -> deleteCustomer();
                case 5 -> printSubscribed();
                default -> terminalView.printErrorMessage("Invalid menu item selected!\n");
            }
        } catch (IOException e) {
            terminalView.printErrorMessage(e.getMessage());
        }
        return true;
    }
    private void printCustomers() throws IOException {
        terminalView.printTable(dao.getDataAsTable());
    }
    private void addCustomer() throws IOException {
        String id = promptId(IdSearchType.NEW);
        CrmModel newCustomer = createCustomerFromInput(id);
        dao.addModel(newCustomer);
    }
    private void updateCustomerById() throws IOException {
        String id = promptId(IdSearchType.EXISTING);
        CrmModel newCustomer = createCustomerFromInput(id);
        dao.updateModelById(id, newCustomer);
    }
    private String promptId(IdSearchType searchType) throws IOException {
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
    private void deleteCustomer() throws IOException {
        String id = promptId(IdSearchType.EXISTING);
        dao.deleteModelById(id);
    }
    private void printSubscribed() throws IOException {
        List<String> subscribedNames = dao.loadData()
            .stream()
            .filter(CrmModel::isSubscribed)
            .map(CrmModel::name)
            .toList();
        terminalView.printGeneralResults(subscribedNames.toString(), "subscribed");
    }

}
