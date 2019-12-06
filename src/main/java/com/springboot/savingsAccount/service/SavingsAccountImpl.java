package com.springboot.savingsAccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.repo.SavingsAccountRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingsAccountImpl implements SavingsAccountInterface {

	private static final Logger log = LoggerFactory.getLogger(SavingsAccountImpl.class);

	
	@Autowired
    SavingsAccountRepo repo;
	@Override
	public Flux<SavingsAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<SavingsAccount> findById(String id) {
		
		return repo.findById(id);
	}

	@Override
	public Mono<SavingsAccount> save(SavingsAccount savingsAccount) {
		
		return repo.save(savingsAccount);
	}

	@Override
	public Mono<SavingsAccount> update(SavingsAccount savingsAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

		s.setNumberAccount(savingsAccount.getNumberAccount());
		s.setBalance(savingsAccount.getBalance());
		s.setState(savingsAccount.getState());
		return repo.save(s);
		});
	}

	@Override
	public Mono<Void> delete(SavingsAccount SavingsAccount) {
		
		return repo.delete(SavingsAccount);
	}
	
	

}
