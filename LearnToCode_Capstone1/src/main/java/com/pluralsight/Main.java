package com.pluralsight;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ShowScreenHome();

        //Transaction transaction = new Transaction(date, time, descricption, vendor, amount)

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
}