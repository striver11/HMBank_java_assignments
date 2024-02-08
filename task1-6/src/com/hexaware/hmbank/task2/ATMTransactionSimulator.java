    package com.hexaware.hmbank.task2;

import java.util.Scanner;

public class ATMTransactionSimulator {
    static void atmTransaction(double balance, String password) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your password: ");
        String inputPassword = scanner.nextLine();

        if (!inputPassword.equals(password)) {
            System.out.println("Incorrect password. Please try again.");
            atmTransaction(balance, password);
            return;
        }

        System.out.println("Please select an option:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Your balance is: " + balance);
                break;
            case 2:
                System.out.println("Enter the amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                if (withdrawAmount > balance) {
                    System.out.println("Insufficient balance.");
                } else if (withdrawAmount % 100 != 0 && withdrawAmount % 500 != 0) {
                    System.out.println("Withdrawal amount must be in multiples of 100 or 500.");
                } else {
                    balance -= withdrawAmount;
                    System.out.println("Withdrawal successful. Your new balance is: " + balance);
                }
                break;
            case 3:
                System.out.println("Enter the amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                balance += depositAmount;
                System.out.println("Deposit successful. Your new balance is: " + balance);
                break;
            case 4:
                System.out.println("Thank you for visiting HMBank. Goodbye!");
                scanner.close();
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }

        System.out.println("Do you want to continue? (y/n)");
        String continueTransaction = scanner.next();
        if (continueTransaction.equalsIgnoreCase("y")) {
            atmTransaction(balance, password);
        } else {
            System.out.println("Thank you for visiting HMBank. Goodbye!");
            scanner.close();
        }
    }

    public static void main(String[] args) {
        System.out.println("Set your password: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        double balance = 10000;
        atmTransaction(balance, password);
        
        scanner.close();
    }
}
