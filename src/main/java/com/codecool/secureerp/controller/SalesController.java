package com.codecool.secureerp.controller;

import com.codecool.secureerp.dao.SalesDao;
import com.codecool.secureerp.model.HrModel;
import com.codecool.secureerp.model.SalesModel;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class SalesController {
    public static final String[] OPTIONS = new String[]{
            "Back to main menu",
            "List transactions",
            "Add new transaction",
            "Update transaction",
            "Remove transaction",
            "Get the transaction that made the biggest revenue",
            "Get the product that made the biggest revenue altogether",
            "Count number of transactions between dates",
            "Sum the price of transactions between dates"
    };
    private final TerminalView terminalView;
    private final SalesDao dao = new SalesDao();
    private List<String> customerIdList;

    public SalesController(TerminalView terminalView) throws IOException {
        this.terminalView = terminalView;
        dao.load();
    }

    public void displayMenu(List<String> customerIdList) {
        this.customerIdList = customerIdList;
        boolean isRunning = true;
        while (isRunning) {
            terminalView.printMenu("Sales Operation", OPTIONS);
            int selectedMenu = Integer.parseInt(terminalView.getInput("Please select one of the following options:"));
            isRunning = invokeMenuItem(selectedMenu);
        }
    }

    public void close() throws IOException {
        dao.save();
    }

    private boolean invokeMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 0 -> {
                return false;
            }
            case 1 -> {
                terminalView.printTable(dao.getDataAsTable());
            }
            case 2 -> {
                dao.addSale(promptSale(true));
            }
            case 3 -> {
                dao.updateSaleById(terminalView.getInput(
                                "Enter the id of transaction you want to update: "),
                        promptSale(false)
                );
            }
            case 4 -> {
                dao.deleteSaleById(terminalView.getInput("Enter the id of transaction you want to delete: "));
            }
            case 5 -> {
                terminalView.printMessage(dao.getSaleWithBiggestRevenue().toString());
            }
            case 6 -> {
                terminalView.printMessage(dao.getBiggestRevenueProduct());
            }
            case 7 -> {
                String[] startInputQuestions = {"Please insert the starting year (inclusive): ", "Please insert the starting month (inclusive): ", "Please insert the starting day (inclusive): "};
                String[] endInputQuestions = {"Please insert the starting year (exclusive): ", "Please insert the starting month (exclusive): ", "Please insert the starting day (exclusive): "};
                String startDate;
                String endDate;

                do {
                    startDate = String.join("-", terminalView.getInputs(startInputQuestions));
                } while (!validateDateInput(startDate));

                do {
                    endDate = String.join("-", terminalView.getInputs(endInputQuestions));
                } while (!validateDateInput(endDate));

                dao.getNumberOfSalesBetweenDates(startDate, endDate);
            }
            case 8 -> {
                String[] startInputQuestions = {"Please insert the starting year (inclusive): ", "Please insert the starting month (inclusive): ", "Please insert the starting day (inclusive): "};
                String[] endInputQuestions = {"Please insert the starting year (exclusive): ", "Please insert the starting month (exclusive): ", "Please insert the starting day (exclusive): "};
                String startDate;
                String endDate;

                do {
                    startDate = String.join("-", terminalView.getInputs(startInputQuestions));
                } while (!validateDateInput(startDate));

                do {
                    endDate = String.join("-", terminalView.getInputs(endInputQuestions));
                } while (!validateDateInput(startDate));

                dao.getSumOfRevenueBetweenDates(startDate, endDate);

            }
            default -> terminalView.printErrorMessage("Invalid menu item selected!\n");
        }
        return true;
    }




    private SalesModel promptSale(boolean checkNoDuplicateId) {
        String id;
        int price = -1;
        String customerId;
        do {
            id = terminalView.getInput("Id: ");
            if (!checkNoDuplicateId || !dao.hasId(id)) break;
            terminalView.printErrorMessage("id already exists");
        } while (true);

        while (true) {
            customerId = terminalView.getInput("Costumer's Id: ");
            if (customerIdList.contains(customerId)) break;
            terminalView.printErrorMessage("Customer id does not exist");
        }
        String product = terminalView.getInput("Product's Name: ");

        boolean isValidPriceAdded = false;
        while (!isValidPriceAdded) {
            try {
                price = Integer.parseInt(terminalView.getInput("Product's Price: "));
                isValidPriceAdded = true;
            } catch (Exception e) {
                terminalView.printErrorMessage("Invalid price input");
            }

        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String transactionDate = dtf.format(now);

        return new SalesModel(id, customerId, product, price, transactionDate);
    }

    private boolean validateDateInput(String dateInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateInput);
            if (dateInput.equals(dateFormat.format(date))) {
                return true;
            }
        } catch (Exception e) {
            terminalView.printErrorMessage("Invalid date input");
        }
        return false;
    }

}

