package com.springboot.savingsAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.CuentaDto;
import com.springboot.savingsAccount.dto.SavingsAccountDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public SavingsAccount convertSavingsAccount(SavingsAccountDto savingsAccountDto) {

                      LOGGER.info("convetir -->"+savingsAccountDto);
		
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
	
	public SavingsAccount convertCurrentAccount(CuentaDto cuentaDto) {

		SavingsAccount  savingsAccount = new SavingsAccount();

		savingsAccount.setName("Cuenta-Ahorro");
		savingsAccount.setNumberAccount(cuentaDto.getNumberAccount());
		savingsAccount.setState(cuentaDto.getState());
		savingsAccount.setBalance(cuentaDto.getBalance());
		savingsAccount.setTea(cuentaDto.getTea());
		savingsAccount.setCreateDate(new Date());
		savingsAccount.setUpdateDate(new Date());
		savingsAccount.setIdOperation(new ArrayList<String>());

		
		return savingsAccount;

	}

}
