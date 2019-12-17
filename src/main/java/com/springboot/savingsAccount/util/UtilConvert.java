package com.springboot.savingsAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.SavingsAccountDto;

@Component
public class UtilConvert {
	
	
	public SavingsAccount convertSavingsAccount(SavingsAccountDto savingsAccountDto) {

		SavingsAccount savingsAccount = new SavingsAccount();

		savingsAccount.setName("Cuenta-Ahorro");
		savingsAccount.setNumberAccount(savingsAccountDto.getNumberAccount());
		savingsAccount.setTea(savingsAccountDto.getTea());
		savingsAccount.setState(savingsAccountDto.getState());
		savingsAccount.setBalance(savingsAccountDto.getBalance());
		savingsAccount.setCreateDate(new Date());
		savingsAccount.setUpdateDate(new Date());
		savingsAccount.setIdOperation(new ArrayList<String>());

		return savingsAccount;

	}

}
