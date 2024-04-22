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
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Prints out any type of non-tabular data
     *
     * @param result String with result to be printed
     * @param label  label String
     */
    public void printGeneralResults(String result, String label) {
        throw new RuntimeException("Not implemented yet");
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
     * @param table 2 dimensional array to be printed as table
     */
    public void printTable(String[][] table) {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Gets single String input from the user
     *
     * @param label the label before the user prompt
     * @return user input as String
     */
    public String getInput(String label) {
        throw new RuntimeException("Not implemented yet");
    }


    /**
     * Gets a list of String inputs from the user
     *
     * @param labels array of Strings with the labels to be displayed before each prompt
     * @return array of user inputs
     */
    public String[] getInputs(String[] labels) {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Prints out error messages to terminal
     *
     * @param message String with error details
     */
    public void printErrorMessage(String message) {
        throw new RuntimeException("Not implemented yet");
    }
}
