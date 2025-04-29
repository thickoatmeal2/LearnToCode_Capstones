package com.pluralsight;

import java.util.*;
import java.util.Scanner;

public class Ledger {
    private Scanner scanner;
    private List<Transaction> transactions;

    public Ledger(Scanner scanner, List<Transaction> transactions) {
        this.scanner = scanner;
        this.transactions = transactions;
    }

    public void showLedgerScreen() {
        String ledgerPrompt = "Ledger Screen (Transaction History):\n" +
                "A) All Transactions\n" +
                "D) Deposits\n" +
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
                case "H":
                    return; // Return to Home Screen
                default:
                    System.out.println("Invalid choice. Please enter A, D, or H.");
            }
        }
    }

    private void displayTransactions(List<Transaction> transactions, String title) {
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