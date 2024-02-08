package com.hexaware.hmbank2.view;

import java.util.Scanner;

import com.hexaware.hmbank2.controller.CustomerController;
import com.hexaware.hmbank2.controller.CustomerInterface;
import com.hexaware.hmbank2.exception.InsufficientFundsException;
import com.hexaware.hmbank2.exception.InvalidAccountException;
import com.hexaware.hmbank2.exception.OverDraftLimitExceededException;

public class BankApp {

    public static void main(String[] args) {
        System.out.println("Welcome to HexaBank");
        CustomerInterface customerController = new CustomerController();
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Enter your choice");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Add Account");
            System.out.println("4. View Account");
  
            System.out.println("5. Perform Operations on Account");

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    customerController.addCustomer();
                    break;
                case 2:
                    customerController.viewCustomer();
                    break;
                case 3:
                    customerController.addAccount();
                    break;
                case 4:
                    customerController.viewAccount();
                    break;
               
                case 5:
                    performAccountOperations(customerController);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

            System.out.println("Do you want to continue? (Y/N): ");
            choice = scanner.next();

        } while (choice.equalsIgnoreCase("Y"));

        System.out.println("Thank you for using HMBank!");
    }

    private static void performAccountOperations(CustomerInterface customerController) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Account ID to perform operations:");
        long accountId = scanner.nextLong();

        System.out.println("Choose Operation:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Calculate Interest");
        System.out.println("4. Transfer"); // New option for transfer

        int operationChoice = scanner.nextInt();

        try {
            switch (operationChoice) {
                case 1:
                    System.out.println("Enter amount to deposit:");
                    float depositAmount = scanner.nextFloat();
                    customerController.deposit(accountId, depositAmount);
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:");
                    float withdrawAmount = scanner.nextFloat();
                    customerController.withdraw(accountId, withdrawAmount);
                    break;
                case 3:
                    customerController.calculateInterest(accountId);
                    break;
                case 4:
                    System.out.println("Enter target Account ID for transfer:");
                    long toAccountId = scanner.nextLong();
                    System.out.println("Enter amount to transfer:");
                    float transferAmount = scanner.nextFloat();
                    customerController.transfer(accountId, toAccountId, transferAmount);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid operation.");
            }
        } catch (InsufficientFundsException | InvalidAccountException | OverDraftLimitExceededException e) {
            System.out.println("Operation failed. " + e.getMessage());
        }
    }

 
}
