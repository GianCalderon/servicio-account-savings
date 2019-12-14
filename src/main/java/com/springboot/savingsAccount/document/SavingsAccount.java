package com.springboot.savingsAccount.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "SavingsAccount")
public class SavingsAccount {
	
	@Id
	private String id;
	
	private String name;
	
	private String numberAccount;
	
	private Double tea;
	
	private String state;
	
	private int balance;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	
	private List<String> idOperation; 

	public SavingsAccount(String name, String numberAccount, Double tea, String state, int balance,
			Date createDate, Date updateDate) {
		
	
		this.name = name;
		this.numberAccount = numberAccount;
		this.tea = tea;
		this.state = state;
		this.balance = balance;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public SavingsAccount() {
	
	}
	
	
	
	
	

}
