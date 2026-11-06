package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ledger {
    private static final String FILE_NAME = "transactions.csv";

    public static void runMenu() {
        boolean inLedger = true;
        while (inLedger) {
            System.out.println("\n--- LEDGER SCREEN ---");
            System.out.println("A) All entries");
            System.out.println("D) Deposits only");
            System.out.println("P) Payments only");
            System.out.println("R) Reports");
            System.out.println("H) Home (Back)");

            String choice = Console.PromptString("Choose an option: ").toUpperCase();
            ArrayList<Transaction> transactions = loadTransactions();

            switch (choice) {
                case "A":
                    System.out.println("\n--- ALL ENTRIES (NEWEST FIRST) ---");
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;
                case "D":
                    System.out.println("\n--- DEPOSITS ONLY ---");
                    for (Transaction t : transactions) {
                        if (t.getAmount() > 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "P":
                    System.out.println("\n--- PAYMENTS ONLY ---");
                    for (Transaction t : transactions) {
                        if (t.getAmount() < 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "R":
                    Report.runMenu(transactions);
                    break;
                case "H":
                    System.out.println("Returning to Home Screen...");
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again!");
            }
        }
    }

    public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    list.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading transactions file.");
        }
        Collections.reverse(list);
        return list;
    }
}
