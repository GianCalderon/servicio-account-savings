package com.springboot.savingsAccount.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccount.client.PersonalClient;
import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.AccountClient;
import com.springboot.savingsAccount.dto.AccountDto;
import com.springboot.savingsAccount.dto.OperationDto;
import com.springboot.savingsAccount.dto.PersonalDto;
import com.springboot.savingsAccount.dto.SavingsAccountDto;
import com.springboot.savingsAccount.repo.SavingsAccountRepo;
import com.springboot.savingsAccount.util.CodAccount;
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
	public Mono<SavingsAccount> findByNumAccount(String numberAccount) {
		
		return repo.findByNumberAccount(numberAccount);
	}

	@Override
	public Mono<SavingsAccount> save(SavingsAccount savingsAccount) {
		
		savingsAccount.setCreateDate(new Date());
		savingsAccount.setUpdateDate(new Date());
		savingsAccount.setIdOperation(new ArrayList<String>());
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
	public Mono<Void> delete(SavingsAccount savingsAccount) {
		
		return repo.delete(savingsAccount);
	}

	
	/* Guarda una cuenta con multiples titulares */
	@Override
	public Mono<SavingsAccountDto> saveHeadlines(SavingsAccountDto savingsAccountDto) {
	
	

		return save(convert.convertSavingsAccount(savingsAccountDto)).flatMap(cuenta -> {

			savingsAccountDto.getHeadlines().forEach(titular -> {

				titular.setIdAccount(cuenta.getId());
				titular.setNameAccount(cuenta.getNameAccount());
				titular.setNumberAccount(cuenta.getNumberAccount());

				client.save(titular).block();

			});

			return Mono.just(savingsAccountDto);
		});
		
	}

	 /* Guarda una cuenta con un titular */
	@Override
	public Mono<PersonalDto> saveHeadline(AccountDto accountDto) {

		return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
		
		int cont = 0;

	     for (int i = 0; i < cuentas.size(); i++) {

				AccountClient obj = cuentas.get(i);

				LOGGER.info("PRUEBA 3 --->" + accountDto.toString());

			    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_SAVINGS_ACCOUNT)) cont++;

			}
	     
			if (cont == 0) {

				return repo.save(convert.convertAccountDto(accountDto)).flatMap(cuenta -> {

					return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {

						LOGGER.info("Flujo Inicial ---->: " + titular.toString());

						titular.setIdAccount(cuenta.getId());
						titular.setNameAccount(cuenta.getNameAccount());
						titular.setNumberAccount(cuenta.getNumberAccount());

						LOGGER.info("Flujo Final ----->: " + titular.toString());

						return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
							
							p.setIdAccount(cuenta.getId());
							return Mono.just(p);
						});

					});

				});

			} else {

				return Mono.empty();
			}

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
	


}