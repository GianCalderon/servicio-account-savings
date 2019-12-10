package com.springboot.savingsAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class SavingsAccountDto {

	private String numberAccount;
	private String state;
	private int balance;
	private List<PersonalDto> holders;
	
}
