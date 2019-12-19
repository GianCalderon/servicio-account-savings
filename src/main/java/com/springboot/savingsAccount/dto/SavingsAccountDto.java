package com.springboot.savingsAccount.dto;

import java.util.List;

import lombok.Data;

@Data
public class SavingsAccountDto {

	
	private Double tea;
	private String state;
	private Double balance;
	
	private List<PersonalDto> headlines;
	
}


