package com.springboot.savingsAccount.util;

import org.springframework.stereotype.Component;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.SavingsAccountDto;

@Component
public class UtilConvert {
	
	
	public SavingsAccount convertSavingsAccount(SavingsAccountDto savingsAccountDto) {

		SavingsAccount savingsAccount = new SavingsAccount();

		savingsAccount.setNumberAccount(savingsAccountDto.getNumberAccount());
		savingsAccount.setState(savingsAccountDto.getState());
		savingsAccount.setBalance(savingsAccountDto.getBalance());

		return savingsAccount;

	}

}
