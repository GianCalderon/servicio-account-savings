package com.springboot.savingsAccount.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PersonalDto {

	private String idAccount;
	private String nameAccount;
	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;
	private List<AccountDto> idCuentas;
	
}
