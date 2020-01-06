package com.springboot.savingsAccount.service;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.AccountDto;
import com.springboot.savingsAccount.dto.ManageOperationDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingsAccountInterface{

	public Flux<SavingsAccount> findAll();
	public Mono<SavingsAccount> findById(String id);
	public Mono<SavingsAccount> save(AccountDto accountDto);
	public Mono<SavingsAccount> update(SavingsAccount savingsAccount, String id);
	public Mono<Void> delete(SavingsAccount savingsAccount);

	public Mono<SavingsAccount> findByNumAccount(String id);
	public Flux<SavingsAccount> findByDni(String id);
	
	public Flux<ManageOperationDto> searchOperations(String dni);
	
    

}
