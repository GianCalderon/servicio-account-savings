package com.springboot.savingsAccount.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "SavingsAccount")
public class SavingsAccount {
	
	@Id
	private String id;
	private String numberAccount;
	private String state;
	private int balance;
	

}
