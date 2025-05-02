package com.pluralsight;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Transaction> transactions = new ArrayList<>();
    private static final File CSV_FILE_PATH = new File("transactions.csv");

    public static void main(String[] args) {
        loadTransactions();
        showHomeScreen();
    }

    private static void showHomeScreen() {
        Ledger ledger = new Ledger(scanner, transactions);
        String homePrompt = "Home Screen:\n" +
                "D) Add Deposit\n" +
                "P) Make Payment\n" +
                "L) Ledger (Transaction History)\n" +
                "X) Exit\n" +
                "Enter your choice: ";

        while (true) {
            System.out.print(homePrompt);
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledger.showLedgerScreen();
                    break;
                case "X":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter D, P, L, or X.");
            }
        }
    }

    private static void addDeposit() {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            String description = "deposit";
            String vendor = "user";

            System.out.print("Enter amount (positive): ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Amount must be positive for deposits.");
                return;
            }

            Transaction transaction = new Transaction(dateTime, description, vendor, amount);
            saveTransaction(transaction);
            System.out.println("Deposit added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Deposit not added: " + e.getMessage());
        }
    }

    private static void makePayment() {
        try {
            LocalDateTime dateTime = LocalDateTime.now();

            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine().trim();

            System.out.print("Enter amount (positive, will be saved as negative): ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }

            Transaction transaction = new Transaction(dateTime, description, vendor, -amount);
            saveTransaction(transaction);
            System.out.println("Payment added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Payment not added: " + e.getMessage());
        }
    }

    private static void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
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
            System.out.println("No existing transactions.csv found at " + CSV_FILE_PATH.getAbsolutePath() + ". A new file will be created when saving.");
        } catch (IOException e) {
            System.out.println("Error reading transactions.csv: " + e.getMessage());
        }
    }

    private static void saveTransaction(Transaction transaction) {
        try {
            // Ensure parent directories exist
            File parentDir = CSV_FILE_PATH.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
                writer.write(transaction.toCsv());
                writer.newLine();
                transactions.add(transaction);
                Collections.sort(transactions);
                System.out.println("Transaction saved to: " + CSV_FILE_PATH.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error saving transaction to " + CSV_FILE_PATH.getAbsolutePath() + ": " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
}
