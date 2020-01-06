package com.springboot.savingsAccount.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccount.client.ManageOperationClient;
import com.springboot.savingsAccount.client.PersonalClient;
import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.AccountDto;
import com.springboot.savingsAccount.dto.ManageOperationDto;
import com.springboot.savingsAccount.repo.SavingsAccountRepo;
import com.springboot.savingsAccount.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingsAccountImpl implements SavingsAccountInterface {


	private static final Logger LOGGER = LoggerFactory.getLogger(SavingsAccountImpl.class);

	
	@Autowired
    SavingsAccountRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	@Autowired
	PersonalClient personalClient;

	@Autowired
	ManageOperationClient manageOperationClient;
	
	
	@Override
	public Flux<SavingsAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<SavingsAccount> findById(String id) {
		
		return repo.findById(id);
	}

	@Override
	public Mono<SavingsAccount> save(AccountDto accountDto) {

		return personalClient.findByNumDoc(accountDto.getNumDoc()).flatMap(persona ->{
		return repo.findByDniAndNameBank(accountDto.getNumDoc(), accountDto.getNameBank()).count().flatMap(AccountCant->{
			LOGGER.info("Cantidad cuentas por dni/banco: "+AccountCant);
			if(AccountCant==0) return repo.save(convert.convertAccountDto(accountDto));
			  else return Mono.empty();
          });
		});
  }

	@Override
	public Mono<SavingsAccount> update(SavingsAccount savingsAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

		s.setNameAccount(savingsAccount.getNameAccount());
		s.setNumberAccount(savingsAccount.getNumberAccount());
		s.setBalance(savingsAccount.getBalance());
		s.setState(savingsAccount.getState());
		s.setTea(savingsAccount.getTea());
		s.setHeadlines(savingsAccount.getHeadlines());
		s.setUpdateDate(new Date());
		
		
		return repo.save(s);
		});
	}
	
	@Override
	public Mono<Void> delete(SavingsAccount savingsAccount) {
		
		return repo.delete(savingsAccount);
	}
	
	@Override
	public Mono<SavingsAccount> findByNumAccount(String numberAccount) {
		
		return repo.findByNumberAccount(numberAccount);
	}
	
	
    public Flux<SavingsAccount> findByDni(String dni) {
		
		return repo.findByDni(dni);
	}

	@Override
	public Flux<ManageOperationDto> searchOperations(String dni) {
		
		
		return repo.findByDni(dni).flatMap(account->{
			
			return manageOperationClient.findByNumberAccount(account.getNumberAccount());

		});
		
		
	}

}