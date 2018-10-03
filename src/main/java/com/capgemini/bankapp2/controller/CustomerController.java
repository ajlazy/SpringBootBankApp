package com.capgemini.bankapp2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.capgemini.bankapp2.Exception.IncorrectPasswordException;
import com.capgemini.bankapp2.Exception.InvalidDetailsException;
import com.capgemini.bankapp2.model.Customer;
import com.capgemini.bankapp2.model.Customer.loginCheck;
import com.capgemini.bankapp2.service.CustomerService;


public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String getHome() {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";

	}

	@ModelAttribute("customer")
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(Model model,@Validated({ loginCheck.class }) Customer customer,BindingResult bindingResult, HttpServletRequest request) {

		
	//if (bindingResult.hasErrors()) {
	 //       return "login";
	//	}
	System.out.println("dgvdrgdgb");
		Cookie cookies[] = request.getCookies();
		if (cookies == null)
			return "enableCookies";
		try {

			customer = customerService.authenticate(customer);
		} catch (InvalidDetailsException e) {
			model.addAttribute("exception", e.getMessage());
			return "exceptionPage";

		}
		// System.out.println(customer);
		if (customer != null) {
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			session.setMaxInactiveInterval(60);
			return "home";
		}
		return null;

	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String getEditProfilePage(Model model, HttpServletRequest request, HttpSession session) {
		request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		model.addAttribute("customer", customer);
		return "editProfile";
	}

	@RequestMapping(value = "/editProfile.do", method = RequestMethod.POST)
	public String editProfile(@ModelAttribute Customer customer, HttpServletRequest request, HttpSession session) {
		customerService.updateProfile(customer);

		session.setAttribute("customer", customer);
		return "successfulUpdate";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "logout";

	}
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String getChangePasswordPage() {
		return "changePassword";

	}

	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String changePassword(@SessionAttribute("customer") Customer customer,Model model, HttpServletRequest request, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		//HttpSession session = request.getSession();
		//Customer customer = (Customer) session.getAttribute("customer");
		//Sessi
		try {
			customerService.updatePassword(customer, oldPassword, newPassword);
		} catch (IncorrectPasswordException e) {

			model.addAttribute("exception", e);
			return "exceptionPage";
		}
		return "successfulPasswordChange";

	}
	
}
