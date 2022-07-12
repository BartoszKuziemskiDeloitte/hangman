package com.wisielec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wisielec {

    private static final List<String> passwords = new ArrayList<>();
    private static final String filename = "passwords.txt";

    public static void addPassword(String password) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
            out.println(password);
        } catch (IOException e) {
            System.out.println("Cannot add the password to file");
        }
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
        Integer passwordNumber = random.nextInt(passwords.size());

        System.out.println(passwordNumber);

        return passwords.get(passwordNumber);
    }


}
