package com.pluralsight;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static String PromptString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static double PromptDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
                return value;
            } catch (Exception e) {
                System.out.println("Invalid number format. Please try again.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
