package com.hexaware.hmbank2.model;


//task8
public class SavingsAccount extends Account {
    private float interestRate;

    public SavingsAccount(long accountNumber, float accountBalance, float interestRate) {
        super(accountNumber, "Savings", accountBalance);
        this.interestRate = interestRate;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        float interest = this.getAccountBalance() * interestRate;
        this.setAccountBalance(this.getAccountBalance() + interest);
        System.out.println("Interest calculated and added. New balance: " + this.getAccountBalance());
    }
}
