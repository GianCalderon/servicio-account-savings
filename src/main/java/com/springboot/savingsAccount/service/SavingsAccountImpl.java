package com.springboot.savingsAccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccount.client.PersonalClient;
import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.CuentaDto;
import com.springboot.savingsAccount.dto.OperationDto;
import com.springboot.savingsAccount.dto.PersonalDto;
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
	PersonalClient client;

	
	
	@Override
	public Flux<SavingsAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<SavingsAccount> findById(String id) {
		
		return repo.findById(id);
	}
	
	@Override
	public Mono<SavingsAccount> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}

	@Override
	public Mono<SavingsAccount> save(SavingsAccount savingsAccount) {
		
		return repo.save(savingsAccount);
	}

	@Override
	public Mono<SavingsAccount> update(SavingsAccount savingsAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

		s.setNameAccount(savingsAccount.getNameAccount());
		s.setNumberAccount(savingsAccount.getNumberAccount());
		s.setBalance(savingsAccount.getBalance());
		s.setState(savingsAccount.getState());
		s.setTea(savingsAccount.getTea());
		s.setUpdateDate(savingsAccount.getUpdateDate());
		s.setCreateDate(savingsAccount.getCreateDate());
		s.setIdOperation(savingsAccount.getIdOperation());
		
		return repo.save(s);
		});
	}
	
	
	@Override
	public Mono<SavingsAccount> updateClient(SavingsAccount savingsAccount, String numAccount) {
		
		return repo.findByNumberAccount(numAccount).flatMap(s -> {

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

			savingsAccountDto.getHeadlines().forEach(titular -> {

				titular.setNameAccount(sa.getNameAccount());
				titular.setIdAccount(sa.getId());

				client.save(titular).block();

			});

			return Mono.just(savingsAccountDto);
		});
		
	}
	
	@Override
	public Mono<SavingsAccount> saveOperation(OperationDto operationDto) {
		
		
		
	return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{
		
	
		if(operationDto.getTipoMovement().equals("debito")) {
			
			p.setBalance(p.getBalance()-operationDto.getAmount());
			return repo.save(p);
			
		}else if(operationDto.getTipoMovement().equals("abono")) {
			
			p.setBalance(p.getBalance()+operationDto.getAmount());
			return repo.save(p);
		}
		
		return repo.save(p);

	});

  }
	
	@Override
	public Mono<PersonalDto> saveAddCuenta(CuentaDto cuentaDto) {
		
	    return repo.save(convert.convertCurrentAccount(cuentaDto)).flatMap(c->{
	    	
	    	return client.findByNumDoc(cuentaDto.getDni()).flatMap(titular->{
	    		
	    		LOGGER.info("Flujo Inicial ---->: "+titular.toString());
	            
	    		
	    		titular.setNameAccount(c.getNameAccount());
	    		titular.setIdAccount(c.getId());
	    		

	             LOGGER.info("Flujo Final ----->: "+titular.toString());
	             
	            return client.update(titular,cuentaDto.getDni());
	            
	 
	    	});
	    	
	    });
	}
	

}