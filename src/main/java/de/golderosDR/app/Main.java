package de.golderosDR.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        while (true) {
            menu.showMainMenu();
            switch (scanner.next()) {
                case "1" -> menu.printAllUsers();
                case "2" -> menu.printUsersByInputtedName();
                case "3" -> menu.save();
                case "4" -> menu.update();
                case "5" -> menu.remove();
                case "6" -> menu.utilSubMenu();
                case "0" -> {
                    System.out.println("Выход...");
                    scanner.close();
                    System.exit(0);
                }
                default -> menu.wrongCommand();
            }
        }

    }
}
