package com.hexaware.hmbank.task3;
import java.util.Scanner;
public class CompoundInterestOnSavingAccount {
    static void calculateFutureBalance(Scanner scanner) {
        // Prompt the user to enter the initial balance, annual interest rate, and the number of years
        System.out.println("Enter the initial balance: ");
        double initialBalance = scanner.nextDouble();

        System.out.println("Enter the annual interest rate (in %): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.println("Enter the number of years: ");
        int years = scanner.nextInt();

        // Calculate the future balance
        double futureBalance = initialBalance * Math.pow((1 + annualInterestRate / 100), years);

        // Display the future balance
        System.out.println("The future balance after " + years + " years will be: " + futureBalance);

        // Ask the user if they want to calculate the balance for another customer
        System.out.println("Do you want to calculate the balance for another customer? (yes/no): ");
        scanner.nextLine();  // Consume newline left-over
        String anotherCustomer = scanner.nextLine();

        if (anotherCustomer.equalsIgnoreCase("yes")) {
            calculateFutureBalance(scanner);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        calculateFutureBalance(scanner);
        scanner.close();
    }
}
