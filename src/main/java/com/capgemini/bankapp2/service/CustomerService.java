package com.capgemini.bankapp2.service;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import com.capgemini.bankapp2.Exception.IncorrectPasswordException;
import com.capgemini.bankapp2.Exception.InvalidDetailsException;
import com.capgemini.bankapp2.model.Customer;

public interface CustomerService {

	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws IncorrectPasswordException;
	Customer authenticate(Customer customer) throws InvalidDetailsException;

}