package com.hexaware.hmbank2.controller;


//import com.hexaware.hmbank2.model.Customer;
import com.hexaware.hmbank2.exception.InsufficientFundsException;
import com.hexaware.hmbank2.exception.InvalidAccountException;
import com.hexaware.hmbank2.exception.OverDraftLimitExceededException;
//import com.hexaware.hmbank2.model.Account;
public interface CustomerInterface {
    void addCustomer();

    void viewCustomer();
    
    void addAccount();

    void viewAccount();

	void deposit(long accountId, float amount);

	void withdraw(long accountId, float amount);

	void calculateInterest(long accountId);

	void transfer(long fromAccountId, long toAccountId, float amount)
			throws InsufficientFundsException, InvalidAccountException, OverDraftLimitExceededException;
}
