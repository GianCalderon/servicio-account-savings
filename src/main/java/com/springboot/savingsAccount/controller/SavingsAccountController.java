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
import com.springboot.savingsAccount.dto.PersonalDto;
import com.springboot.savingsAccount.dto.SavingsAccountDto;
import com.springboot.savingsAccount.service.SavingsAccountImpl;
import com.springboot.savingsAccount.serviceDto.PersonalImplDto;
import com.springboot.savingsAccount.serviceDto.SavingsAccountImplDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/savingsAccount")
public class SavingsAccountController {
	
	
	private static final Logger log =LoggerFactory.getLogger(SavingsAccountController.class);
	
	
	@Autowired
   SavingsAccountImpl service;
	
	@Autowired
	PersonalImplDto serviceDirecto;
	 
	  @GetMapping
	  public Mono<ResponseEntity<Flux<SavingsAccount>>> toList() {

	    return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
	    		.body(service.findAll()));

	  }

	  @GetMapping("/{id}")
	  public Mono<ResponseEntity<SavingsAccount>> search(@PathVariable String id) {

	    return service.findById(id).map(s->ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(s))
					.defaultIfEmpty(ResponseEntity.notFound().build());

		}

		@PostMapping
		public Mono<ResponseEntity<SavingsAccount>> save(@RequestBody SavingsAccount savingsAccount) {
		

			return service.save(savingsAccount).map(s->ResponseEntity
					.created(URI.create("/api/savingsAccount".concat(s.getId())))
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(s));

		}
		
		@PostMapping("/directo")
		public Mono<PersonalDto> saveDtoDirecto(@RequestBody PersonalDto directo) {
		

			return serviceDirecto.save(directo);

		}

		@PutMapping("/{id}")
		public Mono<ResponseEntity<SavingsAccount>> update(@RequestBody SavingsAccount savingsAccount, @PathVariable String id) {

			return service.update(savingsAccount, id).map(s->ResponseEntity
							.created(URI.create("/api/savingsAccount".concat(s.getId())))
							.contentType(MediaType.APPLICATION_JSON_UTF8)
							.body(s))
					.defaultIfEmpty(ResponseEntity.notFound().build());
				
		
		}
		
		@DeleteMapping("/{id}")
		public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
			
			return service.findById(id).flatMap(s->{
				return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
			}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

			
		}
		

		@Autowired
		   SavingsAccountImplDto serviceDto;
//			 
//			  @GetMapping("/dto")
//			  public Mono<ResponseEntity<Flux<SavingsAccountDto>>> toListDto() {
//
//			    return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
//			    		.body(serviceDto.findAll()));
//
//			  }
//
//			  @GetMapping("dto/{id}")
//			  public Mono<ResponseEntity<SavingsAccountDto>> searchDto(@PathVariable String id) {
//
//			    return serviceDto.findById(id).map(s->ResponseEntity.ok()
//							.contentType(MediaType.APPLICATION_JSON_UTF8)
//							.body(s))
//							.defaultIfEmpty(ResponseEntity.notFound().build());
//
//				}
//
				@PostMapping("/saveDto")
				public Mono<SavingsAccountDto> saveDto(@RequestBody SavingsAccountDto savingsAccountDto) {
				
					log.info(savingsAccountDto.toString());

//					return serviceDto.save(savingsAccountDto).map(s->ResponseEntity
//							.created(URI.create("/api/savingsAccountDto"))
//							.contentType(MediaType.APPLICATION_JSON_UTF8)
//							.body(s));
					
					return serviceDto.save(savingsAccountDto);

				}
//
//				@PutMapping("/{id}")
//				public Mono<ResponseEntity<SavingsAccount>> update(@RequestBody SavingsAccountDto savingsAccountDto, @PathVariable String id) {
//
//					return serviceDto.update(savingsAccountDto, id).map(s->ResponseEntity
//									.created(URI.create("/api/savingsAccountDto".concat(s.getId())))
//									.contentType(MediaType.APPLICATION_JSON_UTF8)
//									.body(s))
//							.defaultIfEmpty(ResponseEntity.notFound().build());
//						
//				
//				}
//				
//				@DeleteMapping("/{id}")
//				public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
//					
//					return serviceDto.findById(id).flatMap(s->{
//						return serviceDto.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
//					}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
//
//					
//				}


}
