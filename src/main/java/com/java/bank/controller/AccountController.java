package com.java.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.bank.entity.Account;
import com.java.bank.entity.Transactions;
import com.java.bank.repo.AccountRepo;
//import com.java.bank.entity.Transactions;
import com.java.bank.service.AccountServiceImpl;

import jakarta.websocket.server.PathParam;

@Controller
public class AccountController {

	static {
		System.out.println("Controller Class");
	}

	@Autowired
	private AccountServiceImpl asi;

	@Autowired
	private AccountRepo accountRepo;

	@GetMapping("/ramkrishna")
	public String WellComePage() {

		return "wellcome";
	}

	@GetMapping("/admin")
	public String Admin() {

		return "admin";
	}

	@GetMapping("/user")
	public String User() {

		return "user";
	}

	@GetMapping("/userlogin")
	public String UserLogIn(Model model) {
		model.addAttribute("userlogin", new com.java.bank.entity.UserLogIn());
		return "login";
	}

	@PostMapping("/checklogin")
	public String CheckLogIn(Model model,@RequestParam String username,@RequestParam String password) {
		Account account = accountRepo.findByUserName(username);
		 if (account != null) {
			 
			 Long anumber=account.getAnumber();
			 List<Transactions> transactions=asi.getTransactionsByAccountNo(anumber);
			 model.addAttribute("transaction", transactions);
			 
			if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
				String usermsg = "Hi " + account.getName() + "...!";
		String balance="Your Current Balance :"+ account.getBalance();
				model.addAttribute("usermsg", usermsg);
				model.addAttribute("balance", balance);
			} else {

				model.addAttribute("usermsg", "Wrong Psssword");
				return "login";
			}
		} else {
			model.addAttribute("msg", "Enter valid username or password");
			return "login";
		}

		return "userdashbord";
	}
	
	
	//****************************************************************************************
	
	@GetMapping("/adminlogin")
	public String AdminLogIn(Model model) {
		model.addAttribute("userlogin", new com.java.bank.entity.UserLogIn());
		return "adminlogin";
	}

	@PostMapping("/checkadminlogin")
	public String CheckAdminLogIn(Model model,@RequestParam String username,@RequestParam String password) {
           String uname="admin@yes";
           String pass="admin@123";
		 
			if (uname.equals(username) && pass.equals(password)) {
				 
 				return "admin";
			} else {

				 
				return "login";
			}
		 

		 
	}
	
	//****************************************************************************************

	@GetMapping("/create")
	public String CreateForm(Model model) {
		model.addAttribute("create", new Account());
		return "create";
	}
	
 
	@PostMapping("/save")
	public String createAccount(Model model, Account account, BindingResult result) {
		asi.createAccount(account);
		model.addAttribute("msg", "created");
		return "create";
	}
	
	//****************************************************************************************


	@GetMapping("/getbyid")
	public String getIdForm() {
		 
		return "getbyaccount";
	}
	
	

	
	@GetMapping("/account")
	public String Search(@RequestParam Long anumber, Model model) {
		
		Account account = asi.getByAccountNo(anumber);
		System.out.println(account);

		model.addAttribute("account", account);
		return "showaccount";
	}
	
	//****************************************************************************************

	@GetMapping("/accounts")
	public String getAllAccounts(Model model) {
		List<Account> accounts = asi.getAllAccount();
		model.addAttribute("account", accounts);
		return "showaccounts";
	}

	//****************************************************************************************

 
	
	@GetMapping("/dp")
	public String depositForm() {
		 
		return "depositform";
	}
	
 
	@PostMapping("/deposit")
	public String deposit(@RequestParam Long anumber, @RequestParam double amount, Model model) {
		String result = asi.deposit(anumber, amount);
		model.addAttribute("depositResult", result);

		if (result.contains("successful")) {
			model.addAttribute("msg", "Deposite Success");
			return "depositform"; // View for successful deposit
		} else {
			model.addAttribute("msg", "Deposit Error");
			return "depositform"; // View for failed deposit
		}
	}
	
	//****************************************************************************************

   //This service is use in Bank and ATM only
	@PostMapping("/withdraw")
	public String withdraw(@RequestParam Long anumber, @RequestParam double amount) {
		String result = asi.withdraw(anumber, amount);

		if (result.contains("successful")) {
			return result;
		} else {
			return result;
		}
	}

	//****************************************************************************************
    
	@GetMapping("/transferform")
	public String TransferForm() {
    	
    	return "transferfrom";
    }
    
    
	@PostMapping("/transfer")
	public String transferAmount(@RequestParam Long sourceAccountNo, @RequestParam Long targetAccountNo,
			@RequestParam double amount,Model model) {

		String result = asi.transfer(sourceAccountNo, targetAccountNo, amount);
         model.addAttribute("message", result);
 		if (result.contains("successful")) {
			return "msg";
		} else {
			return "transferfrom";
		}
	}
	
	//****************************************************************************************
    
	@GetMapping("/removeform")
	public String RemoveAccount() {
    	
    	
    	return "removeuser";
    }
	
	@GetMapping("/removed")
	public String RemoveAccount(Model model,Long anumber) {
		
		asi.RemoveAccount(anumber);
		model.addAttribute("remove", "Removed Success");
		
		return "msg";
	}
	
	
	//****************************************************************************************

	@GetMapping("/balance/{anumber}")
	public Double getBalance(@PathVariable Long anumber) {
		return asi.getBalance(anumber);
	}
	
	//****************************************************************************************


	@GetMapping("/transactions/{anumber}")
	public String getTransactionsByAccountNo(Long anumber,Model model){
		
		List<Transactions> transactions=asi.getTransactionsByAccountNo(anumber);
		model.addAttribute("transaction", transactions);
		
		return "transactions";
	}

}
