package com.pluralsight;
import java.time.*;
import java.time.format.*;

public class Transaction implements Comparable<Transaction> {
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

    public LocalDateTime getDateTime() { return dateTime; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public double getAmount() { return amount; }

    @Override
    public int compareTo(Transaction other) {
        return other.dateTime.compareTo(this.dateTime); // Sort newest first
    }

    public String toCsv() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("%s|%s|%s|%s|%.2f",
                dateTime.format(dateFormatter),
                dateTime.format(timeFormatter),
                description,
                vendor,
                amount);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("%-12s %-10s %-20s %-15s $%-10.2f",
                dateTime.format(dateFormatter),
                timeFormatter.format(dateTime),
                description,
                vendor,
                amount);
    }
