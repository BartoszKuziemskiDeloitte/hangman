package com.wisielec;

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
                    return;
                default:
                    break;
            }
        }
    }

    private static void printOptions() {
        System.out.println("Choose option: ");
        System.out.println("1. Play");
        System.out.println("2. Add password");
        System.out.println("3. Quit");
    }

    private static void initGame() {
        String randomPassword = Database.getRandomPassword();
        if (randomPassword == null) {
            System.out.println("There are no passwords in the file. Please add at least one");
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
            String userInput = getUserInput();
            if (!isInputAChar(userInput)) {
                System.out.println("Please write only one letter");
                continue;
            }
            char letter = userInput.charAt(0);
            game.checkLetter(letter);
            printGuessedPassword(game);
            Human.printHuman(game.getHealth());
            if (game.checkIfWin()) {
                System.out.println("You have won!");
                return;
            }
        }
        System.out.println("You have lost");
    }

    private static boolean isInputAChar(String userInput) {
        return userInput.length() <= 1;
    }

    private static void printGuessedPassword(Game game) {
        System.out.println("Password: ");
        System.out.println(game.getGuessedPassword());
    }

    private static void addPassword() {
        System.out.println("Please enter new password to add");
        if (!Database.addPassword(getUserInput())) {
            System.out.println("Password can contain letters only");
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
