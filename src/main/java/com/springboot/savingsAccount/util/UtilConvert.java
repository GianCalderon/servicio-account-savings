package com.springboot.savingsAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.AccountDto;
import com.springboot.savingsAccount.dto.SavingsAccountDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public SavingsAccount convertSavingsAccount(SavingsAccountDto savingsAccountDto) {

                      LOGGER.info("convetir -->"+savingsAccountDto);
		
		SavingsAccount savingsAccount = new SavingsAccount();

		
		savingsAccount.setNameAccount(CodAccount.NAME_SAVINGS_ACCOUNT);
		savingsAccount.setNumberAccount(CodAccount.COD_SAVINGS_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		savingsAccount.setTea(savingsAccountDto.getTea());
		savingsAccount.setState(savingsAccountDto.getState());
		savingsAccount.setBalance(savingsAccountDto.getBalance());
		savingsAccount.setCreateDate(new Date());
		savingsAccount.setUpdateDate(new Date());
		savingsAccount.setIdOperation(new ArrayList<String>());

		return savingsAccount;

	}
	
	public SavingsAccount convertAccountDto(AccountDto accountDto) {
		
		 LOGGER.info("convetir 1 -->"+accountDto.toString());

		SavingsAccount  savingsAccount = new SavingsAccount();

		savingsAccount.setNameBank(accountDto.getNameBank());
		savingsAccount.setNumDoc(accountDto.getNumDoc());
		savingsAccount.setNameAccount(CodAccount.NAME_SAVINGS_ACCOUNT);
		savingsAccount.setNumberAccount(CodAccount.COD_SAVINGS_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		savingsAccount.setState("Activo");
		savingsAccount.setBalance(accountDto.getBalance());
		savingsAccount.setTea(12.5);
		savingsAccount.setCreateDate(new Date());
		savingsAccount.setUpdateDate(new Date());
		savingsAccount.setIdOperation(new ArrayList<String>());

		 LOGGER.info("convetir 2-->"+savingsAccount.toString());
		return savingsAccount;

	}

}
