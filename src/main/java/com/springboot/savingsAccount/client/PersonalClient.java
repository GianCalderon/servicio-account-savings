package com.springboot.savingsAccount.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.savingsAccount.dto.AccountDto;
import com.springboot.savingsAccount.dto.CuentaDto;
import com.springboot.savingsAccount.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClient {
	
	
   private static final Logger LOGGER = LoggerFactory.getLogger(PersonalClient.class);
	
   
   WebClient client = WebClient.create("http://localhost:8001/api/personal");	
   
//	@AUTOWIRED
//	PRIVATE WEBCLIENT CLIENT;
	
	public Flux<PersonalDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(PersonalDto.class));
	}


	public Mono<PersonalDto> findById(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("id", id);
		return client.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<PersonalDto> save(PersonalDto personalDto) {
		
		LOGGER.info("listo a enviar: "+personalDto.toString());
		
		return client.post().uri("/guardar")
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(personalDto))
			   .retrieve()
			   .bodyToMono(PersonalDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		

		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<PersonalDto> update(PersonalDto personalDto, String id) {
		
		LOGGER.info("listo enviar Actualizar: "+personalDto.toString());
		
		return client.put()
				   .uri("/{id}",Collections.singletonMap("id",id))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(personalDto)
				   .retrieve()
				   .bodyToMono(PersonalDto.class);
	}
	
	
	
	public Mono<PersonalDto> findByNumDoc(String dni) {
		
	
		return client.get()
				.uri("/doc/{dni}",Collections.singletonMap("dni",dni))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
		        
	}
	
	public Flux<AccountDto> valid(String dni) {

		return client.get()
				.uri("/valid/{dni}",Collections.singletonMap("dni",dni))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(AccountDto.class);
			
	}
	
	

}
