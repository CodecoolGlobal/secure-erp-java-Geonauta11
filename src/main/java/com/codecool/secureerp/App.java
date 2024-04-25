package com.codecool.secureerp;


import com.codecool.secureerp.controller.CrmController;
import com.codecool.secureerp.controller.HrController;
import com.codecool.secureerp.controller.MainController;
import com.codecool.secureerp.controller.SalesController;
import com.codecool.secureerp.view.TerminalView;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        TerminalView terminalView = new TerminalView(scanner);
        CrmController crmController = new CrmController(terminalView);
        SalesController salesController = new SalesController(terminalView);
        HrController hrController = new HrController(terminalView);
        MainController mainController = new MainController(crmController, salesController, hrController, terminalView);
        mainController.menu();
    }
}
