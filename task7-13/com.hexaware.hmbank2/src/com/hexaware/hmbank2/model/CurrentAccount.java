package com.hexaware.hmbank2.model;


import com.hexaware.hmbank2.exception.InsufficientFundsException;
//task9
public class CurrentAccount extends Account {
    private static final float OVERDRAFT_LIMIT = 1000.0f;

    public CurrentAccount(long accountNumber, float accountBalance) {
        super(accountNumber, "Current", accountBalance);
    }

    public float getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }

    @Override
    public void withdraw(float amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid withdrawal amount. Please enter a positive value.");
            }

            float availableBalance = this.getAccountBalance() + OVERDRAFT_LIMIT;
            if (amount > availableBalance) {
                throw new InsufficientFundsException("Overdraft limit exceeded. Cannot withdraw.");
            }

            this.setAccountBalance(this.getAccountBalance() - amount);
            System.out.println("Withdrawal successful. New balance: " + this.getAccountBalance());
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
