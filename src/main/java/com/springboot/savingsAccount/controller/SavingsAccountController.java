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

import com.springboot.savingsAccount.document.SavingsAccount;
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

	@GetMapping
	public Mono<ResponseEntity<Flux<SavingsAccount>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccount>> search(@PathVariable String id) {

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<SavingsAccount>> save(@RequestBody SavingsAccount savingsAccount) {

		return service.save(savingsAccount)
				.map(s -> ResponseEntity.created(URI.create("/api/savingsAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccount>> update(@RequestBody SavingsAccount savingsAccount,
			@PathVariable String id) {

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

	@PostMapping("/saveDto")
	public Mono<ResponseEntity<SavingsAccountDto>> saveDto(@RequestBody SavingsAccountDto savingsAccountDto) {

		LOGGER.info(savingsAccountDto.toString());

		return service.saveDto(savingsAccountDto).map(s -> ResponseEntity.created(URI.create("/api/savingsAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	

}
