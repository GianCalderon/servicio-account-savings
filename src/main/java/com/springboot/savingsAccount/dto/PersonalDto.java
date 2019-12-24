package com.springboot.savingsAccount.dto;

import lombok.Data;

@Data
public class PersonalDto {

	private String idAccount;
	private String numberAccount="xxxxxxxxxxxxxx";
	private String nameAccount="Cuenta-Ahorro";
	
	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;

	
}
