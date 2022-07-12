package com.wisielec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuConsole {

    public static void printMenu() {
        while (true) {
            System.out.println("Choose option: ");
            System.out.println("1. Get password");
            System.out.println("2. Add password");
            System.out.println("3. Quit");

            int menu = Integer.parseInt(getUserInput());

            switch (menu) {
                case 1:
                    System.out.println(Wisielec.getRandomPassword());
                    break;
                case 2:
                    System.out.println("Please enter new password to add");
                    Wisielec.addPassword(getUserInput());
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    private static String getUserInput() {
        String input = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = br.readLine();
        } catch (IOException exception) {
            System.out.println("Please enter correct values");
        }
        return input;
    }

}
