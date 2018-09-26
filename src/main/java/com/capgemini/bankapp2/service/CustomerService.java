package com.capgemini.bankapp2.service;

import com.capgemini.bankapp2.model.Customer;

public interface CustomerService {

	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) ;

}