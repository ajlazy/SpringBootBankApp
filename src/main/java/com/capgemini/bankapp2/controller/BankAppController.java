package com.capgemini.bankapp2.controller;

import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapp2.Exception.IncorrectPasswordException;
import com.capgemini.bankapp2.Exception.InsufficientBalanceException;
import com.capgemini.bankapp2.Exception.InvalidAccountException;
import com.capgemini.bankapp2.Exception.InvalidDetailsException;
import com.capgemini.bankapp2.model.BankAccount;
import com.capgemini.bankapp2.model.Customer;
import com.capgemini.bankapp2.model.Customer.loginCheck;
import com.capgemini.bankapp2.service.BankAccountService;
import com.capgemini.bankapp2.service.CustomerService;

@EnableAutoConfiguration
@Controller
public class BankAppController {

	@Autowired
	CustomerService customerService;
	@Autowired
	BankAccountService bankAccountService;

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

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(Model model,@Validated({ loginCheck.class }) @ModelAttribute Customer customer,BindingResult bindingResult, HttpServletRequest request) {

		
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

	@RequestMapping(value = "/transferAmount", method = RequestMethod.GET)
	public String getTransferAmountPage(Model model, HttpServletRequest request, HttpSession session) {
//			request.getSession();
//            Customer customer=(Customer) session.getAttribute("customer");
//            BankAccount bankAccount=customer.getCustomerAccount();
//			model.addAttribute("bankAccount",bankAccount);
		return "transferAmount";

	}

	@RequestMapping(value = "/transferAmount.do", method = RequestMethod.POST)
	public String transferAmount(Model model, @RequestParam long fromAccount, @RequestParam long toAccount,
			@RequestParam double amount) {

		try {
			bankAccountService.fundTransfer(fromAccount, toAccount, amount);
		} catch (InsufficientBalanceException | InvalidAccountException e) {
			model.addAttribute("exception", e.getMessage());
			return "exceptionPage";
		}
		return "successfulTransfer";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String getChangePasswordPage() {
		return "changePassword";

	}

	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String changePassword(Model model, HttpServletRequest request, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		try {
			customerService.updatePassword(customer, oldPassword, newPassword);
		} catch (IncorrectPasswordException e) {

			model.addAttribute("exception", e);
			return "exceptionPage";
		}
		return "successfulPasswordChange";

	}

	@RequestMapping(value = "/checkBalance", method = RequestMethod.GET)
	public String getBalance(HttpServletRequest request) {
		return "getBalance";

	}

}
