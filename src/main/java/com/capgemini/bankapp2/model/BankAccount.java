package com.capgemini.bankapp2.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BankAccount {

	@Positive(message = "AccountId cannot be negative")
	@NotNull (message = "AccountId cannot be blank")
	private long accountId;
	
	@NotBlank(message = "AccountType cannot be blank")
	private String accountType;
	
	@NotNull (message = "Balance cannot be blank")
	@Positive(message = "Balance cannot be negative")
	private double accountBalance;
	public BankAccount() {
		super();
	}
	public BankAccount(long accountId, String accountType, double accountBalance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

}