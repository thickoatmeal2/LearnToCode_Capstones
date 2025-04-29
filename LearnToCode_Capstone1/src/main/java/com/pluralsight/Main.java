package com.pluralsight;
import java.io.*;
import java.time.*;
import java.util.*;


public class Main {
    private static scanner scanner = new scanner(System.in);

    //this method displays the home screen
    public static void showHomeScreen() {
        String homePrompt = "Home Screen: What would you like to do today?\n" +
                "D) Add Deposit\n" +
                "P) Make Payments\n" +
                "L) Ledger\n" +
                "X) exit\n";

        while (true) {
            System.out.print(homePrompt);
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment():
                    break;
                case "L":
                    showLedgerScreen():
                    break;
                case "X":
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter D, P, L, or X");

            }
        }
    }

    //this method displays the deposit screen
    private static void addDeposit() {
        //try catch method to check for invalid input; if input is invalid, user will be prompted to try again
        try {
            System.out.print("Enter date in (YY-MM-DD format");
            String dateStr = scanner.nextLine().trim();
            LocalDate date = LocaleDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.print("Enter time (HH:MM:SS): ");
            String timeStr = scanner.nextLine().trim();
            LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));

            System.out.print("Enter amount"):
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Amount must be positive for deposits")
                return;
            }

            LocalDateTime dateTime = LocalDateTime.of(date, time);
            Transaction transaction = new Transaction(dateTime, description, vendor, amount);
            saveTransaction(transaction);
            System.out.println("Deposit added successfully");
        } catch (Exception e) {
            System.out.println("Invalid input. Try again" + e.getMessage());
        }
    }

    private static void makePayment() {
        try {
            System.out.print("Enter time (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine().trim();
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.print("Enter time (HH:MM:SS): ");
            String timeStr = scanner.nextLine().trim();
            LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPatter("HH:mm:ss"));

            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine().trim();

            System.out.print("Enter amount");
            double amount = Double.parseDouble(Scanner.nextLine().)
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }

            LocalDateTime dateTime = LocalDateTime.of(date, time);
            Transaction transaction = new Transaction(dateTime, description, vendor, -amount);
            saveTransaction(transaction);
            System.out.println("Payment added successfully");
        } catch (Exception e) {
            System.out.println("Invalid input. Payment not added.: " + e.getMessage());
        }
    }

    //this method displays te ledger screen
    private static void showLedgerScreen() {
        String ledgerPrompt = "Ledger Screen (Transaction History):\n" +
                "A) All Transactions\n" +
                "D) Deposits\n" +
                "C) Home\n" +
                "Enter your choice:";

        while (true) {
            System.out.print(ledgerPrompt);
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
            }
            case "A":
                displayTransactions(transactions, "All Transactions");
                break;
            case "D":
                List<Transaction> deposits = new ArrayList<>();
                for (Transaction t : transactions)
        }
        if (t.getAmount() > 0) {
            deposits.add(t);
        }
    }

    displayTransactions(deposits, "Deposits");
                break;
            case"H":
                    return;
    default:
            System.out.println("Invalid choice, please enter A, D, or H");
}
}
}
