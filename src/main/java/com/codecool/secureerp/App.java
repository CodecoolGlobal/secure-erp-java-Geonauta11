package com.codecool.secureerp;


import com.codecool.secureerp.controller.CRMController;
import com.codecool.secureerp.controller.HRController;
import com.codecool.secureerp.controller.MainController;
import com.codecool.secureerp.controller.SalesController;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        TerminalView terminalView = new TerminalView(scanner);
        CRMController crmController = new CRMController(terminalView);
        SalesController salesController = new SalesController(terminalView);
        HRController hrController = new HRController(terminalView);
        MainController mainController = new MainController(crmController, salesController, hrController, terminalView);
        try {
            mainController.menu();
        } finally {
            mainController.tearDown();
        }
    }
}
