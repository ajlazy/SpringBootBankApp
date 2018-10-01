package com.capgemini.bankapp2.serviceimpl;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp2.Exception.FailedToUpdateException;
import com.capgemini.bankapp2.Exception.IncorrectPasswordException;
import com.capgemini.bankapp2.Exception.InvalidDetailsException;
import com.capgemini.bankapp2.Exception.UserNotFoundException;
import com.capgemini.bankapp2.model.Customer;
import com.capgemini.bankapp2.repository.CustomerRepository;
import com.capgemini.bankapp2.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer authenticate(Customer customer) throws DataAccessException{
		try {
		Customer customer1= customerRepository.authenticate(customer);
		return customer1;
		}

		catch(DataAccessException e)
		{
			UserNotFoundException exception=new UserNotFoundException("user not found");
			exception.initCause(e);
			throw exception;
			
		}
		}
	@Override
	
	public Customer updateProfile(Customer customer) {
		try {
	return customerRepository.updateProfile(customer);
		}
	catch(EmptyResultDataAccessException e)
	{
		FailedToUpdateException exception=new FailedToUpdateException("Failed to update profile");
		exception.initCause(e);
		throw exception;
	}
	
	}
	
		
		

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws IncorrectPasswordException{
	
		if(customerRepository.updatePassword(customer, oldPassword, newPassword)==false)
			throw new IncorrectPasswordException("Incorrect pAssword");
		return customerRepository.updatePassword(customer, oldPassword, newPassword);
	
	
	}

}