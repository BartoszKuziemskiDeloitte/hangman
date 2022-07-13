package com.wisielec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuConsole {

    public static void printMenu() {
        while (true) {
            System.out.println("Choose option: ");
            System.out.println("1. Play");
            System.out.println("2. Add password");
            System.out.println("3. Quit");

            int menu = Integer.parseInt(getUserInput());

            switch (menu) {
                case 1:
                    printGame();
                    break;
                case 2:
                    System.out.println("Please enter new password to add");
                    Database.addPassword(getUserInput());
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    private static void printGame() {
        Game game = new Game(Database.getRandomPassword());
        System.out.println(game.getUserPassword());
        while (game.getHealth() > 0) {
            System.out.println("Type letter to guess: ");
            char guess = getUserInput().charAt(0);
            System.out.println(game.checkLetter(guess));
            if (game.checkIfWin(guess)) {
                System.out.println("You have won!");
                return;
            }
            System.out.println("You have " + game.getHealth() + " guesses left");
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
