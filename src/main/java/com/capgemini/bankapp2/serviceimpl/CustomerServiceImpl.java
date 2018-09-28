package com.capgemini.bankapp2.serviceimpl;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp2.Exception.IncorrectPasswordException;
import com.capgemini.bankapp2.Exception.InvalidDetailsException;
import com.capgemini.bankapp2.model.Customer;
import com.capgemini.bankapp2.repository.CustomerRepository;
import com.capgemini.bankapp2.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer authenticate(Customer customer) throws InvalidDetailsException {
		Customer customer1= customerRepository.authenticate(customer);
		if(customer1.getCustomerName()==null) {
			throw new InvalidDetailsException("Wrong username or password");
	}
		return customer1;
		}

	@Override
	public Customer updateProfile(Customer customer) {
	return customerRepository.updateProfile(customer);
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws IncorrectPasswordException{
	
		if(customerRepository.updatePassword(customer, oldPassword, newPassword)==false)
			throw new IncorrectPasswordException("Incorrect pAssword");
		return customerRepository.updatePassword(customer, oldPassword, newPassword);
		
	}

}