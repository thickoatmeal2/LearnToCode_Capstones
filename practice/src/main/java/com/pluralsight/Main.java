package com.pluralsight;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

class Transaction implements Comparable<Transaction> {
    private LocalDateTime dateTime;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDateTime dateTime, String description, String vendor, double amount) {
        this.dateTime = dateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Transaction other) {
        return other.dateTime.compareTo(this.dateTime); // Sort newest first
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("%-12s %-10s %-20s %-15s $%-10.2f",
                dateTime.format(dateFormatter),
                dateTime.format(timeFormatter),
                description,
                vendor,
                amount);
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Transaction> transactions = new ArrayList<>();
    private static final String CSV_FILE = "transactions.csv";

    public static void main(String[] args) {
        loadTransactions();
        boolean exit = false;
        do {
            ShowScreenHome();
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());
                switch (option) {
                    case 4:
                        ShowScreenLedger();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        exit = true;
                        break;
                    default:
                        System.out.println("Option " + option + " selected (not implemented).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid option, try again");
            }
        } while (!exit);
    }

    private static void ShowScreenHome() {
        //Displays the homescreen, asks user what they want to do
        String homeScreenPrompt = "Hello User!\n" +
                "What would you like to do today? (1)\n" +
                "Make deposit( 2)\n" +
                "Make payment (debit) (3)\n" +
                "View recent transactions (ledger) (4)\n" +
                "View stock market prices (5)\n" +
                "View crypto prices (6)\n" +
                "Exit (7)\n";

        int option;

        //Use try catch for error handling
        do {
            System.out.println(homeScreenPrompt);
            try {
                option = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Not a valid option, try again");
                option = -1;
                continue;
            }
        } while (option != 0);
    }

    private static void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(
                                parts[0] + "T" + parts[1],
                                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                        );
                        String description = parts[2];
                        String vendor = parts[3];
                        double amount = Double.parseDouble(parts[4]);
                        transactions.add(new Transaction(dateTime, description, vendor, amount));
                    } catch (Exception e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
            Collections.sort(transactions); // Sort newest first
        } catch (FileNotFoundException e) {
            System.out.println("No existing transactions.csv found.");
        } catch (IOException e) {
            System.out.println("Error reading transactions.csv: " + e.getMessage());
        }
    }

    private static void ShowScreenLedger() {
        String ledgerPrompt = "Ledger Options:\n" +
                "A) All Transactions\n" +
                "D) Deposits\n" +
                "P) Payments\n" +
                "R) Reports\n" +
                "H) Home\n" +
                "Enter your choice: ";

        while (true) {
            System.out.print(ledgerPrompt);
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayTransactions(transactions, "All Transactions");
                    break;
                case "D":
                    List<Transaction> deposits = new ArrayList<>();
                    for (Transaction t : transactions) {
                        if (t.getAmount() > 0) {
                            deposits.add(t);
                        }
                    }
                    displayTransactions(deposits, "Deposits");
                    break;
                case "P":
                    List<Transaction> payments = new ArrayList<>();
                    for (Transaction t : transactions) {
                        if (t.getAmount() < 0) {
                            payments.add(t);
                        }
                    }
                    displayTransactions(payments, "Payments");
                    break;
                case "R":
                    System.out.println("Reports not implemented yet.");
                    break;
                case "H":
                    return; // Return to Home Screen
                default:
                    System.out.println("Invalid choice. Please enter A, D, P, R, or H.");
            }
        }
    }

    private static void displayTransactions(List<Transaction> transactions, String title) {
        if (transactions.isEmpty()) {
            System.out.println("No " + title.toLowerCase() + " found.");
            return;
        }
        System.out.println("\n" + title + ":");
        System.out.printf("%-12s %-10s %-20s %-15s %-10s%n", "Date", "Time", "Description", "Vendor", "Amount");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println();
    }
}