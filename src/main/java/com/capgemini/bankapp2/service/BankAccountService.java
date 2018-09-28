package com.capgemini.bankapp2.service;

import com.capgemini.bankapp2.Exception.InsufficientBalanceException;
import com.capgemini.bankapp2.Exception.InvalidAccountException;

public interface BankAccountService {

	public double getBalance(long accountId);
	public double withdraw(long accountId, double amount) throws InsufficientBalanceException ;
	public double deposit(long accountId, double amount);
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws InsufficientBalanceException, InvalidAccountException ;

}