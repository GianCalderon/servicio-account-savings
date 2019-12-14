package com.springboot.savingsAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.savingsAccount.document.SavingsAccount;

import reactor.core.publisher.Mono;

public interface SavingsAccountRepo extends ReactiveMongoRepository<SavingsAccount, String> {
	
	  public Mono<SavingsAccount> findByNumberAccount(String numberAccount);
	  


}
