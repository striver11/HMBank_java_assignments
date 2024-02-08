package com.hexaware.hmbank2.model;

import com.hexaware.hmbank2.exception.*;

public class Account {
    private long accountNumber;
    private String accountType;
    private float accountBalance;
    private int customerId;

    public Account() {
        // Default constructor
    }

    public Account(long accountNumber, String accountType, float accountBalance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void deposit(float amount) {
        if (amount > 0) {
            this.accountBalance += amount;
            System.out.println("Deposit successful. New balance: " + this.accountBalance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    public void withdraw(float amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid withdrawal amount. Please enter a positive value.");
            }

            if (amount > this.accountBalance) {
                throw new InsufficientFundsException("Insufficient funds. Cannot withdraw.");
            }

            this.accountBalance -= amount;
            System.out.println("Withdrawal successful. New balance: " + this.accountBalance);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void calculateInterest() {
        // Assuming some interest calculation logic for savings accounts
        if (this.getAccountType().equalsIgnoreCase("Savings")) {
            float interestRate = 0.05f; // 5% interest rate (you can adjust this)
            float interest = this.accountBalance * interestRate;
            this.accountBalance += interest;
            System.out.println("Interest calculated and added. New balance: " + this.accountBalance);
        } else {
            System.out.println("Interest can only be calculated for Savings accounts.");
        }
    }
    
    //task8
    public void deposit(int amount) {
        deposit((float) amount);
    }

    public void deposit(double amount) {
        deposit((float) amount);
    }
    
    public void withdraw(int amount) {
        withdraw((float) amount);
    }

    public void withdraw(double amount) {
        withdraw((float) amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", accountBalance=" + accountBalance +
                ", customerId=" + customerId +
                '}';
    }
}
