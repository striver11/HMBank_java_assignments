package com.hexaware.hmbank2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hexaware.hmbank2.model.Account;
import com.hexaware.hmbank2.model.Customer;
import com.hexaware.hmbank2.exception.InsufficientFundsException;
import com.hexaware.hmbank2.exception.InvalidAccountException;
import com.hexaware.hmbank2.exception.OverDraftLimitExceededException;

public class CustomerController implements CustomerInterface {
    List<Customer> customerList = new ArrayList<>();
    List<Account> accountList = new ArrayList<>();
    Customer customer;
    Account account;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void addCustomer() {
        customer = new Customer();
        System.out.println("Enter Customer ID:");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        customer.setCustomerId(customerId);
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        customer.setFirstName(firstName);
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
        customer.setLastName(lastName);
        System.out.println("Enter Email Address:");
        String emailAddress = scanner.nextLine();
        customer.setEmailAddress(emailAddress);
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();
        customer.setPhoneNumber(phoneNumber);
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        customer.setAddress(address);

        customerList.add(customer);
        System.out.println("Customer " + customerId + " added Successfully !!!");
    }

    @Override
    public void viewCustomer() {
        System.out.println("*** Customer List ****");
        customerList.forEach((customer) -> System.out.println(customer));
    }

    @Override
    public void addAccount() {
        System.out.println("Enter Customer ID to add an account:");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (isCustomerPresent(customerId)) {
            account = new Account();
            account.setCustomerId(customerId);
            System.out.println("Enter Account Number:");
            long accountNumber = scanner.nextLong();
            account.setAccountNumber(accountNumber);
            scanner.nextLine(); // Consume the newline character
            System.out.println("Enter Account Type (e.g., Savings, Current):");
            String accountType = scanner.nextLine();
            account.setAccountType(accountType);
            System.out.println("Enter Initial Account Balance:");
            float balance = scanner.nextFloat();
            account.setAccountBalance(balance);

            accountList.add(account);
            System.out.println("Account " + accountNumber + " added Successfully !!!");
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
        }
    }

    @Override
    public void viewAccount() {
        System.out.println("*** Account List ****");
        accountList.forEach((account) -> System.out.println(account));
    }

    @Override
    public void deposit(long accountId, float amount) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accountId) {
                acc.deposit(amount);
                System.out.println("Deposit successful. New balance: " + acc.getAccountBalance());
                return;
            }
        }
        System.out.println("Account with ID " + accountId + " not found.");
    }

    @Override
    public void withdraw(long accountId, float amount) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accountId) {
                try {
                    acc.withdraw(amount);
                    System.out.println("Withdrawal successful. New balance: " + acc.getAccountBalance());
                } catch (InsufficientFundsException e) {
                    System.out.println("Withdrawal failed. " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Account with ID " + accountId + " not found.");
    }


    @Override
    public void calculateInterest(long accountId) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accountId && acc.getAccountType().equalsIgnoreCase("Savings")) {
                acc.calculateInterest();
                System.out.println("Interest calculated and added. New balance: " + acc.getAccountBalance());
                return;
            }
        }
        System.out.println("Savings account with ID " + accountId + " not found.");
    }

    @Override
    public void transfer(long fromAccountId, long toAccountId, float amount)
            throws InsufficientFundsException, InvalidAccountException, OverDraftLimitExceededException {
        Account fromAccount = null;
        Account toAccount = null;

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == fromAccountId) {
                fromAccount = acc;
            } else if (acc.getAccountNumber() == toAccountId) {
                toAccount = acc;
            }

            // Break the loop if both accounts are found
            if (fromAccount != null && toAccount != null) {
                break;
            }
        }

        if (fromAccount == null || toAccount == null) {
            throw new InvalidAccountException("One or both accounts not found.");
        }

        // Check for overdraft limit in the 'fromAccount' (if it's a Current account)
        if (fromAccount.getAccountType().equalsIgnoreCase("Current")) {
            float overdraftLimit = 1000.0f; // You can adjust this limit
            if (amount > fromAccount.getAccountBalance() + overdraftLimit) {
                throw new OverDraftLimitExceededException("Overdraft limit exceeded. Cannot transfer.");
            }
        } else if (amount > fromAccount.getAccountBalance()) {
            throw new InsufficientFundsException("Insufficient funds. Cannot transfer.");
        }

        // Withdraw from the 'fromAccount'
        fromAccount.withdraw(amount);
        System.out.println("Transfer successful. Withdrawal from " + fromAccountId + ". New balance: " + fromAccount.getAccountBalance());

        // Deposit into the 'toAccount'
        toAccount.deposit(amount);
        System.out.println("Transfer successful. Deposit to " + toAccountId + ". New balance: " + toAccount.getAccountBalance());
    }

    private boolean isCustomerPresent(int customerId) {
        for (Customer c : customerList) {
            if (c.getCustomerId() == customerId) {
                return true;
            }
        }
        return false;
    }
}
