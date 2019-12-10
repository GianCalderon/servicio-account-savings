package com.springboot.savingsAccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccount.client.PersonalClient;
import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.SavingsAccountDto;
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
	PersonalClient webCLient;
	
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
	public Mono<Void> delete(SavingsAccount savingsAccount) {
		
		return repo.delete(savingsAccount);
	}

	@Override
	public Mono<SavingsAccountDto> saveDto(SavingsAccountDto savingsAccountDto) {
	
	

		return save(convert.convertSavingsAccount(savingsAccountDto)).flatMap(sa -> {

			savingsAccountDto.getHolders().forEach(p -> {

				p.setIdCuenta(sa.getId());

				webCLient.save(p).block();

			});

			return Mono.just(savingsAccountDto);
		});
		
	}
	
	

}
