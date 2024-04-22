package com.codecool.secureerp.view;

import java.util.Scanner;

public class TerminalView {

    private final Scanner scanner;

    public TerminalView(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prints a single message to the terminal
     *
     * @param message information to be printed
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints options in standard menu format like this:
     * Main Menu:
     * (1) Store manager
     * (2) Human resources manager
     * (3) Inventory manager
     * (0) Exit program
     *
     * @param title   the title of the menu (first row)
     * @param options array of all available options in menu as Strings
     */
    public void printMenu(String title, String[] options) {
//        System.out.println(title);
//        String s = "";
//        for (int i = options.length - 1; i >= 0; i--) {
//            if(i == 0) {
//                s += "(" + i +") " + options[i];
//            } else {
//                s = "(" + i +") "  + options[i] + "\n" + s;
//            }
//        }
//        System.out.println(s);

        System.out.println(title);
        StringBuilder s = new StringBuilder();
        for (int i = options.length - 1; i >= 0; i--) {
            if(i == 0) {
                s.append("(").append(i).append(") ").append(options[i]);
            } else {
                s.insert(0, "(" + i + ") " + options[i] + "\n");
            }
        }
        System.out.println(s);
    }

    /**
     * Prints out any type of non-tabular data
     *
     * @param result String with result to be printed
     * @param label  label String
     */
    public void printGeneralResults(String result, String label) {
        System.out.println(label + ": " + result);
    }

    /*
     /--------------------------------\
     |   id   |   product  |   type   |
     |--------|------------|----------|
     |   0    |  Bazooka   | portable |
     |--------|------------|----------|
     |   1    | Sidewinder | missile  |
     \--------------------------------/
    */

    /**
     * Prints tabular data like above example
     *
     * @param table 2-dimensional array to be printed as table
     */
    public void printTable(String[][] table) {
        for(String[] row : table) {
            for(String cell : row) {
                System.out.print("|" + cell + " ");
            }
            System.out.println("|");
        }
    }

    /**
     * Gets single String input from the user
     *
     * @param label the label before the user prompt
     * @return user input as String
     */
    public String getInput(String label) {
        System.out.println(label + ":");
        return scanner.nextLine();
    }


    /**
     * Gets a list of String inputs from the user
     *
     * @param labels array of Strings with the labels to be displayed before each prompt
     * @return array of user inputs
     */
    public String[] getInputs(String[] labels) {
        String[] inputs = new String[labels.length];

        for(int i = 0; i < labels.length; i++) {
            inputs[i] = getInput(labels[i]);
        }

        return inputs;
    }

    /**
     * Prints out error messages to terminal
     *
     * @param message String with error details
     */
    public void printErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
}
