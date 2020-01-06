package com.springboot.savingsAccount.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.savingsAccount.document.SavingsAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingsAccountRepo extends ReactiveMongoRepository<SavingsAccount, String> {
	
	 
	public Mono<SavingsAccount> findByNumberAccount(String numberAccount);
	  
	@Query("{'headlines.numDoc': ?0 }") 
	public Flux<SavingsAccount> findByDni(String dni);
	
	@Query("{'headlines.numDoc': ?0 , 'nameBank': ?1}") 
	public Flux<SavingsAccount> findByDniAndNameBank(String dni , String nameBank);
	
	  
	  

	  


}
