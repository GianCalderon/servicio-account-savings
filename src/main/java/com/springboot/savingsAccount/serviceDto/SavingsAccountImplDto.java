package com.springboot.savingsAccount.serviceDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.SavingsAccountDto;
import com.springboot.savingsAccount.repo.SavingsAccountRepo;
import com.springboot.savingsAccount.service.SavingsAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingsAccountImplDto {

	private static final Logger log = LoggerFactory.getLogger(SavingsAccountImplDto.class);
	
	@Autowired
	SavingsAccountImpl service;
	
	@Autowired
	PersonalImplDto serviceDto;

	
	public Flux<SavingsAccountDto> findAll() {
		
		return null;
	}

	
	public Mono<SavingsAccountDto> findById(String id) {
		
		return null;
	}

	
	public Mono<SavingsAccountDto> save(SavingsAccountDto savingsAccountDto) {
		
		
		log.info(savingsAccountDto.toString());
		
		return service.save(convertSavingsAccount(savingsAccountDto)).flatMap(x->{
			
			savingsAccountDto.getHolders().forEach(h-> {
				
				h.setIdCuenta(x.getId());
			
				  serviceDto.save(h);
				
				
			});
			
			return Mono.just(savingsAccountDto);
		});
	}

	
	public Mono<SavingsAccountDto> update(SavingsAccountDto savingsAccountDto, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Mono<Void> delete(SavingsAccountDto savingsAccountDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SavingsAccount convertSavingsAccount(SavingsAccountDto savingsAccountDto) {

		SavingsAccount  savingsAccount = new SavingsAccount();

		savingsAccount.setNumberAccount(savingsAccountDto.getNumberAccount());
		savingsAccount.setState(savingsAccountDto.getState());
		savingsAccount.setBalance(savingsAccountDto.getBalance());
		
		
		return savingsAccount;

	}

	
	
	

}
