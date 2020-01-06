package com.springboot.savingsAccount.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.savingsAccount.document.Headline;
import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.AccountDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public SavingsAccount convertAccountDto(AccountDto accountDto) {
		
		 LOGGER.info("convetir 1 -->"+accountDto.toString());
		 
		 List<Headline> listHeadline=new ArrayList<Headline>();
		 Headline headline=new Headline();
		 headline.setNumDoc(accountDto.getNumDoc());
		 listHeadline.add(headline);

		SavingsAccount  savingsAccount = new SavingsAccount();

		savingsAccount.setNameBank(accountDto.getNameBank());
		savingsAccount.setHeadlines(listHeadline);
		savingsAccount.setNameAccount(CodAccount.NAME_SAVINGS_ACCOUNT);
		savingsAccount.setNumberAccount(CodAccount.COD_SAVINGS_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		savingsAccount.setState("Activo");
		savingsAccount.setBalance(accountDto.getBalance());
		savingsAccount.setTea(12.5);
		savingsAccount.setCreateDate(new Date());
		savingsAccount.setUpdateDate(new Date());
		

		 LOGGER.info("convetir 2-->"+savingsAccount.toString());
		return savingsAccount;

	}

}
