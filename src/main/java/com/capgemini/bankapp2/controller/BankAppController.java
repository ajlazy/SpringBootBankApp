package com.capgemini.bankapp2.controller;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.bankapp2.model.Customer;
import com.capgemini.bankapp2.service.BankAccountService;
import com.capgemini.bankapp2.service.CustomerService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
@EnableAutoConfiguration
@Controller
public class BankAppController {
	
	@Autowired
	CustomerService customerService;

	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}
		@RequestMapping(value="/header",method=RequestMethod.GET)
		public String getHome()
		{
			
			return "home";
		}
		
		@RequestMapping(value="login",method=RequestMethod.GET)
		public String getLoginPage(Model model)
		{
			model.addAttribute("customer",new Customer());
			return "login";
			
		}
		
		
		@RequestMapping(value="login.do",method=RequestMethod.POST)
		public String login(@ModelAttribute Customer customer,HttpServletRequest request)
		{
			customer=customerService.authenticate(customer);
			if(customer!= null) {
			HttpSession session=request.getSession();
			session.setAttribute("customer", customer);
			return "home";
			}
			return null;
				
				
		}
		
		
	}

