package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuConsole {

    public static void printMenu() {
        while (true) {
            printOptions();

            int menu = 0;
            try {
                menu = Integer.parseInt(getUserInput());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter correct values");
            }

            switch (menu) {
                case 1:
                    initGame();
                    break;
                case 2:
                    addPassword();
                    break;
                case 3:
                    deleteAllPassword();
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }

    private static void deleteAllPassword() {
        Database.deleteAllPasswords();
    }

    private static void printOptions() {
        System.out.println("Choose option: ");
        System.out.println("1. Play");
        System.out.println("2. Add password");
        System.out.println("3. Delete all passwords");
        System.out.println("4. Quit");
    }

    private static void initGame() {
        String randomPassword = Database.getPassword();
        if (randomPassword == null) {
            System.out.println("There are no passwords in the database. Please add at least one");
            return;
        }
        Game game = new Game(randomPassword);

        printGame(game);
    }

    private static void printGame(Game game) {
        printGuessedPassword(game);
        while (game.getHealth() > 0) {
            System.out.println("You have " + game.getHealth() + " guesses left");
            System.out.println("Type letter to guess: ");
            Character userInput = getUserChar();
            if (userInput == null) {
                System.out.println("Please write only one letter");
                continue;
            }
            game.checkLetter(userInput);
            printGuessedPassword(game);
            Human.printHuman(game.getHealth());
            if (game.checkIfWin()) {
                System.out.println("You have won!");
                return;
            }
        }
        System.out.println("You have lost");
    }

    private static Character getUserChar() {
        String userInput = getUserInput();
        if (userInput.length() != 1) {
            return null;
        }
        return userInput.charAt(0);
    }

    private static void printGuessedPassword(Game game) {
        System.out.println("Password: ");
        System.out.println(game.getGuessedPassword());
    }

    private static void addPassword() {
        System.out.println("Please enter new password to add");
        if (!Database.addPassword(getUserInput())) {
            System.out.println("Password can contain letters and spaces only");
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
        return input.toLowerCase();
    }

}
