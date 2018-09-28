package com.capgemini.bankapp2.repositoryimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp2.model.BankAccount;
import com.capgemini.bankapp2.repository.BankAccountRepository;


@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) {
		double balance = jdbcTemplate.queryForObject("SELECT  accountBalance FROM bankAccounts WHERE accountId = ?",
				new Object[] {accountId}, Double.class);
		return balance;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		int count = jdbcTemplate.update("UPDATE bankAccounts SET accountBalance = ? WHERE accountId = ?",
				new Object[] { newBalance, accountId });
		return count != 0;
	}



	
	
	

	
}