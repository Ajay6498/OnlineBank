package com.java.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.bank.entity.Account;
//import com.java.bank.entity.Transactions;
import com.java.bank.service.AccountServiceImpl;

@RestController
public class AccountController {
	
	static {
		System.out.println("Controller Class");
	}

	@Autowired
	private AccountServiceImpl asi;
	
//	@GetMapping("/create")
//	public String CreateForm(Model model) {
//		
//		model.addAttribute("create", new Account());
//		
//		return "createform";
//	}

	@PostMapping("/save")
	public String createAccount(@RequestBody Account account) {
		String acc = asi.createAccount(account);
		return acc;
	}

	@GetMapping("/account/{anumber}")
	public Optional<Account> getbyid(@PathVariable Long anumber) {
		Optional<Account> employee = asi.getByAccountNo(anumber);
		return employee;
	}

	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
		List<Account> accounts = asi.getAllAccount();
		return accounts;
	}

	@PostMapping("/deposit")
	public String deposit(@RequestParam Long anumber, @RequestParam double amount) {
		String result = asi.deposit(anumber, amount);

		if (result.contains("successful")) {
			return result;
		} else {
			return result;
		}
	}

	@PostMapping("/withdraw")
	public String withdraw(@RequestParam Long anumber, @RequestParam double amount) {
		String result = asi.withdraw(anumber, amount);

		if (result.contains("successful")) {
			return result;
		} else {
			return result;
		}
	}

	@PostMapping("/transfer")
	public String transferAmount(@RequestParam Long sourceAccountNo, @RequestParam Long targetAccountNo,
			@RequestParam double amount) {

		String result = asi.transfer(sourceAccountNo, targetAccountNo, amount);

		// You can customize the response based on the result
		if (result.contains("successful")) {
			return result;
		} else {
			return result;
		}
	}
	

	@GetMapping("/balance/{anumber}")
	public Double getBalance(@PathVariable Long anumber) {
		return asi.getBalance(anumber);
	}
	
	
//	@GetMapping("/transactions/{anumber}")
//	public List<Transactions> getTransactionsByAccountNo(Long anumber){
//		
//		return asi.getTransactionsByAccountNo(anumber);
//	}

}
