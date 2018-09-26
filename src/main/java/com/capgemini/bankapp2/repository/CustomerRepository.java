package com.capgemini.bankapp2.repository;

import com.capgemini.bankapp2.model.Customer;

public interface CustomerRepository {

	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
}