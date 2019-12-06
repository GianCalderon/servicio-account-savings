package com.springboot.savingsAccount.service;

import com.springboot.savingsAccount.document.SavingsAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingsAccountInterface{

	public Flux<SavingsAccount> findAll();
	public Mono<SavingsAccount> findById(String id);
	public Mono<SavingsAccount> save(SavingsAccount SavingsAccount);
	public Mono<SavingsAccount> update(SavingsAccount SavingsAccount ,String id);
	public Mono<Void> delete(SavingsAccount SavingsAccount);
	
}
