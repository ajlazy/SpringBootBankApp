package com.capgemini.bankapp2.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp2.Exception.InsufficientBalanceException;
import com.capgemini.bankapp2.Exception.InvalidAccountException;
import com.capgemini.bankapp2.repository.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements com.capgemini.bankapp2.service.BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public double getBalance(long accountId) {
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws InsufficientBalanceException {

		double newBalance = getBalance(accountId) - amount;
		if (newBalance >= 0) {
			bankAccountRepository.updateBalance(accountId, newBalance);
			return newBalance;
		}
		throw new InsufficientBalanceException("You dont have sufficient balance");
	}

	@Override
	public double deposit(long accountId, double amount) {
		double newBalance = getBalance(accountId) + amount;
		if (bankAccountRepository.updateBalance(accountId, newBalance))
			return newBalance;
		return 0;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws InvalidAccountException, InsufficientBalanceException{
		
					double balance=withdraw(fromAcc, amount);
					if(balance!=-1)
					{
						if(deposit(toAcc, amount)==-1)
						{
							deposit(fromAcc, amount);
							throw new InvalidAccountException("Account doesnt exist");
						}
					}
					return false;
				
				}
}