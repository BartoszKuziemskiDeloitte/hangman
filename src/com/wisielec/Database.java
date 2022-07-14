package com.wisielec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {

    private static final List<String> passwords = new ArrayList<>();
    private static final String filename = "passwords.txt";

    public static boolean addPassword(String password) {
        if (!isPasswordCorrect(password)) {
            return false;
        }
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
            out.println(password);
        } catch (IOException e) {
            System.out.println("Cannot add the password to file");
        }
        return true;
    }

    private static void readPasswords() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                passwords.add(line);
            }
        } catch (IOException exception) {
            System.out.println("Cannot read the file");
        }
    }

    public static String getRandomPassword() {
        readPasswords();
        Random random = new Random();
        int passwordNumber = random.nextInt(passwords.size());

        return passwords.get(passwordNumber);
    }

    public static boolean isPasswordCorrect(String password) {
        char[] chars = password.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }


}
