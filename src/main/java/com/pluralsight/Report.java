package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class Report {
    public static void runMenu(ArrayList<Transaction> transactions) {
        boolean inReports = true;
        LocalDate now = LocalDate.now();

        while (inReports) {
            System.out.println("\n--- REPORTS SCREEN ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String choice = Console.PromptString("Choose an option: ");

            switch (choice) {
                case "1":
                    System.out.println("\n--- MONTH TO DATE ---");
                    for (Transaction t : transactions) {
                        LocalDate tDate = LocalDate.parse(t.getDate());
                        if (tDate.getYear() == now.getYear() && tDate.getMonth() == now.getMonth()) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "2":
                    System.out.println("\n--- PREVIOUS MONTH ---");
                    LocalDate previousMonth = now.minusMonths(1);
                    for (Transaction t : transactions) {
                        LocalDate tDate = LocalDate.parse(t.getDate());
                        if (tDate.getYear() == previousMonth.getYear() && tDate.getMonth() == previousMonth.getMonth()) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "3":
                    System.out.println("\n--- YEAR TO DATE ---");
                    for (Transaction t : transactions) {
                        LocalDate tDate = LocalDate.parse(t.getDate());
                        if (tDate.getYear() == now.getYear()) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "4":
                    System.out.println("\n--- PREVIOUS YEAR ---");
                    int previousYear = now.getYear() - 1;
                    for (Transaction t : transactions) {
                        LocalDate tDate = LocalDate.parse(t.getDate());
                        if (tDate.getYear() == previousYear) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "5":
                    String vendorSearch = Console.PromptString("Enter vendor name: ");
                    System.out.println("\n--- SEARCH BY VENDOR: " + vendorSearch + " ---");
                    for (Transaction t : transactions) {
                        if (t.getVendor().equalsIgnoreCase(vendorSearch)) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "0":
                    System.out.println("Returning to Ledger Screen...");
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again!");
            }
        }
    }
}
