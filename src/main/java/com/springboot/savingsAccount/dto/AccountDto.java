package com.springboot.savingsAccount.dto;

import lombok.Data;

@Data
public class AccountDto {
	
	private String numDoc;
    private String nameBank;
	private Double balance;
	
}
