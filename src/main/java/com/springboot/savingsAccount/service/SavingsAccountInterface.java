package com.springboot.savingsAccount.service;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.OperationDto;
import com.springboot.savingsAccount.dto.SavingsAccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingsAccountInterface{

	public Flux<SavingsAccount> findAll();
	public Mono<SavingsAccount> findById(String id);
	public Mono<SavingsAccount> save(SavingsAccount savingsAccount);
	public Mono<SavingsAccount> update(SavingsAccount savingsAccount, String id);
	public Mono<Void> delete(SavingsAccount savingsAccount);
	
	public  Mono<SavingsAccountDto> saveDto(SavingsAccountDto savingsAccountDto);
	
	public Mono<SavingsAccount> updateClient(SavingsAccount savingsAccount, String numAccount);
	
	public Mono<SavingsAccount> findByNumAccount(String id);
	
	public Mono<SavingsAccount> saveOperation(OperationDto operationDto);
	
}
