package com.springboot.savingsAccount.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SavingsAccount")
public class SavingsAccount {
	
	@Id
	private String id;
	private String numberAccount;
	private String state;
	private int balance;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumberAccount() {
		return numberAccount;
	}
	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	@Override
	public String toString() {
		return "SavingsAccount [id=" + id + ", numberAccount=" + numberAccount + ", state=" + state
				+ ", availableBalance=" + balance + "]";
	}
	
	

	
	
	

}
