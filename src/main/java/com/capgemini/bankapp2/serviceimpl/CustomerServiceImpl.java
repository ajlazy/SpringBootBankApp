package com.capgemini.bankapp2.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp2.model.Customer;
import com.capgemini.bankapp2.repository.CustomerRepository;
import com.capgemini.bankapp2.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer authenticate(Customer customer) {
		return customerRepository.authenticate(customer);
	}

	@Override
	public Customer updateProfile(Customer customer) {
	return customerRepository.updateProfile(customer);
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword){
	
		return customerRepository.updatePassword(customer, oldPassword, newPassword);
		
	}

}