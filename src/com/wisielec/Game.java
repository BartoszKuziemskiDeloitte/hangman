package com.wisielec;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int maxHealth = 5;
    private final String password;
    private String guessedPassword;
    private int health;
    private final List<Character> guessedChars = new ArrayList<>();

    public Game(String password) {
        this.password = password;
        this.health = maxHealth;
        initGuessedPassword();
    }

    public int getHealth() {
        return health;
    }

    public String getGuessedPassword() {
        return guessedPassword;
    }

    public void checkLetter(char letter) {
        if (password.contains(String.valueOf(letter))) {
            guessedChars.add(letter);
        } else {
            health--;
        }
        setGuessedPassword();
    }

    private void initGuessedPassword() {
        char[] charsPassword = password.toCharArray();
        for (int i = 0; i < password.length(); i++) {
            charsPassword[i] = '_';
        }
        guessedPassword = String.copyValueOf(charsPassword);
    }

    public void setGuessedPassword() {
        char[] charsPassword = password.toCharArray();
        char[] output = charsPassword;

        for (int i = 0; i < charsPassword.length; i++) {
            char out = '_';
            for (int j = 0; j < guessedChars.size(); j++) {
                if (charsPassword[i] == guessedChars.get(j)) {
                    out = guessedChars.get(j);
                    break;
                }
            }
            if (charsPassword[i] == ' ') {
                out = ' ';
            }
            output[i] = out;
        }
        guessedPassword = String.copyValueOf(output);
    }

    public boolean checkIfWin(char letter) {
        return guessedPassword.equals(password);
    }
}
