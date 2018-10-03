package com.capgemini.bankapp2.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.apache.jasper.tagplugins.jstl.core.When;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoInitializationException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.bankapp2.controller.CustomerController;
import com.capgemini.bankapp2.model.BankAccount;
import com.capgemini.bankapp2.model.Customer;
import com.capgemini.bankapp2.service.CustomerService;

public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testLogin() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerEmail("sam@gmail.com");
		customer.setCustomerPassword("s1");
		Customer customer2 = new Customer(2, "SAM", "s1", "sam@gmail.com", "abaa", LocalDate.now(),
				new BankAccount());
		when(customerService.authenticate(customer)).thenReturn(customer2);

		mockMvc.perform(post("/login.do").flashAttr("customer", customer)).andExpect(view().name("home"))
				.andExpect(status().isOk());

	}
	
	

}
