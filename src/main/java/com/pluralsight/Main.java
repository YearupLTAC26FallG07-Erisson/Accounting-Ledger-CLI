package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String FILE_NAME = "transactions.csv";

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- HOME SCREEN ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String choice = Console.PromptString("Choose an option: ").toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    Ledger.runMenu();
                    break;
                case "X":
                    System.out.println("Thank you for using the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again!");
            }
        }
    }

    private static void addDeposit() {
        System.out.println("\n--- ADD DEPOSIT ---");
        String date = Console.PromptString("Enter date (YYYY-MM-DD): ");
        String time = Console.PromptString("Enter time (HH:MM:SS): ");
        String description = Console.PromptString("Enter description: ");
        String vendor = Console.PromptString("Enter vendor: ");
        double amount = Console.PromptDouble("Enter amount: ");

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String record = String.format("%s|%s|%s|%s|%.2f\n", date, time, description, vendor, amount);
            writer.write(record);
            System.out.println("Deposit saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving deposit.");
        }
    }

    private static void makePayment() {
        System.out.println("\n--- MAKE PAYMENT (DEBIT) ---");
        String date = Console.PromptString("Enter date (YYYY-MM-DD): ");
        String time = Console.PromptString("Enter time (HH:MM:SS): ");
        String description = Console.PromptString("Enter description: ");
        String vendor = Console.PromptString("Enter vendor: ");
        double amount = Console.PromptDouble("Enter amount: ");

        if (amount > 0) {
            amount = amount * -1;
        }

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String record = String.format("%s|%s|%s|%s|%.2f\n", date, time, description, vendor, amount);
            writer.write(record);
            System.out.println("Payment saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving payment.");
        }
    }
}
