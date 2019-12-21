package com.springboot.savingsAccount.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.savingsAccount.client.PersonalClient;
import com.springboot.savingsAccount.document.SavingsAccount;
import com.springboot.savingsAccount.dto.AccountDto;
import com.springboot.savingsAccount.dto.CuentaDto;
import com.springboot.savingsAccount.dto.OperationDto;
import com.springboot.savingsAccount.dto.PersonalDto;
import com.springboot.savingsAccount.dto.SavingsAccountDto;
import com.springboot.savingsAccount.service.SavingsAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/savingsAccount")
public class SavingsAccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SavingsAccountController.class);

	@Autowired
	SavingsAccountImpl service;
	
	@Autowired
	PersonalClient client;

	@GetMapping
	public Mono<ResponseEntity<Flux<SavingsAccount>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccount>> search(@PathVariable String id) {
		
		LOGGER.info("NUMERO DE CUENTA SavinAcount :--->"+id);

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@PostMapping
	public Mono<ResponseEntity<SavingsAccountDto>> saveDto(@RequestBody SavingsAccountDto savingsAccountDto) {

		LOGGER.info("Controller ----> "+savingsAccountDto.toString());

		return service.saveDto(savingsAccountDto).map(s -> ResponseEntity.created(URI.create("/api/savingsAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	

	@PutMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccount>> update(@RequestBody SavingsAccount savingsAccount,
			@PathVariable String id) {
		
		
		LOGGER.info("CUENTA PARA GUARDAR :--->"+savingsAccount.toString());

		return service.update(savingsAccount, id)
				.map(s -> ResponseEntity.created(URI.create("/api/savingsAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping("/operation")
	public Mono<ResponseEntity<SavingsAccount>> operation(@RequestBody OperationDto operationDto) {

		LOGGER.info(operationDto.toString());

		return service.saveOperation(operationDto).map(s -> ResponseEntity.created(URI.create("/api/savingsAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	
	
	@GetMapping("/cuenta/{numAccount}")
	public Mono<ResponseEntity<SavingsAccount>> searchByNumDoc(@PathVariable String numAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numAccount);

		return service.findByNumAccount(numAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@PostMapping("/addAccount")
	public Mono<ResponseEntity<PersonalDto>> saveAddDto(@RequestBody CuentaDto cuentaDto) {

		LOGGER.info("Controller ---> :"+cuentaDto.toString());

		return service.saveAddCuenta(cuentaDto).map(s -> ResponseEntity.created(URI.create("/api/currentAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	
	@GetMapping("/valid/{dni}")
	public Flux<AccountDto> searchCuenta(@PathVariable String dni) {
		

		return client.valid(dni);

	}
	
	@PostMapping("/add")
	public Mono<ResponseEntity<PersonalDto>> saveAdd(@RequestBody CuentaDto cuentaDto) {

		LOGGER.info("Controller ---> :"+cuentaDto.toString());

		return service.valid(cuentaDto).map(s -> ResponseEntity.created(URI.create("/api/currentAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	
	
	

}
