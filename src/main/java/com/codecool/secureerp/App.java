package com.codecool.secureerp;


import com.codecool.secureerp.controller.CRMController;
import com.codecool.secureerp.controller.HRController;
import com.codecool.secureerp.controller.MainController;
import com.codecool.secureerp.controller.SalesController;
import com.codecool.secureerp.view.TerminalView;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TerminalView terminalView = new TerminalView(scanner);
        CRMController crmController = new CRMController(terminalView);
        SalesController salesController = new SalesController(terminalView);
        HRController hrController = new HRController(terminalView);

        MainController mainController = new MainController(crmController, salesController, hrController, terminalView);

        mainController.menu();
    }
}
