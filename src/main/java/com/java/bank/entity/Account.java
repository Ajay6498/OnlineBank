package com.java.bank.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
	@Id
	private Long anumber;
	private String name;
	private String address;
	private Double balance; 
	private String password;
	//private List<Transactions> transactions;
	
	static {
		System.out.println("Account Class");
	}
	
	public Long getAnumber() {
		return anumber;
	}
	public void setAnumber(Long anumber) {
		this.anumber = anumber;
	}
//	public List<Transactions> getTransactions() {
//		return transactions;
//	}
//	public void setTransactions(List<Transactions> transactions) {
//		this.transactions = transactions;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account(Long anumber, String name, String address, Double balance, String password) {
		super();
		this.anumber = anumber;
		this.name = name;
		this.address = address;
		this.balance = balance;
		this.password = password;
		 
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
	
}
