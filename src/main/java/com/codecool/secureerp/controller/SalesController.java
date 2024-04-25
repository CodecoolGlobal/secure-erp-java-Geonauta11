package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.SalesDao;
import com.codecool.secureerp.model.CrmModel;
import com.codecool.secureerp.model.SalesModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;

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

    public SalesController(TerminalView terminalView) throws IOException {
        this.terminalView = terminalView;
        dao.load();
    }

    public void displayMenu() {

        boolean isRunning = true;
        while (isRunning) {
            terminalView.printMenu("Customer Relationship", OPTIONS);
            int selectedMenu = Integer.parseInt(terminalView.getInput("Please select one of the following options:"));
            //isRunning = invokeMenuItem(selectedMenu);
        }
    }
    public void close() throws IOException{
        dao.save();
    }

//    private boolean invokeMenuItem(int selectedMenu){
//        switch (selectedMenu) {
//            case 0 -> {
//                return false;
//            }
//            case 1 -> {
//                terminalView.printTable(dao.getDataAsTable());
//            }
//            case 2 -> {
//                String[] questions = {};
//                String[] answers = terminalView.getInputs(questions);
//
//                SalesModel newSale = new SalesModel();
//                dao.addSale();
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
//        return true;
//    }


//    private SalesModel promptSale(boolean checkNoDuplicateId) {
////        String id;
////        int price = -1;
////        do {
////            id = terminalView.getInput("Id: ");
////            if (!checkNoDuplicateId || !dao.hasId(id)) break;
////            terminalView.printErrorMessage("id already exists");
////        } while (true);
////        String customerId = terminalView.getInput("Costumer's Id: ");
////        String product = terminalView.getInput("Product's Name: ");
////
////        boolean isValidPriceAdded = false;
////        while (!isValidPriceAdded){
////            try {
////                price = Integer.parseInt(terminalView.getInput("Product's Price: "));
////                isValidPriceAdded = true;
////            } catch (Exception e){
////                terminalView.printErrorMessage("Invalid price input");
////            }
////
////        }
////
////        String transactionDate = terminalView.getInput("Id: ");;
////
////        return new SalesModel(id, customerId, product, price, transactionDate);
////    }
}
