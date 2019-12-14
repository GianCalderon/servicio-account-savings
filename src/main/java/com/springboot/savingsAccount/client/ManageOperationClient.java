package com.springboot.savingsAccount.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.savingsAccount.dto.OperationDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ManageOperationClient {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(ManageOperationClient.class);
	
	@Autowired
	private WebClient client;
	
	public Flux<OperationDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(OperationDto.class));
	}


	public Mono<OperationDto> findById(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return client.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(OperationDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<OperationDto> save(OperationDto operationDto) {
		
		LOGGER.info("listo a enviar: "+operationDto.toString());
		
		return client.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(operationDto))
			   .retrieve()
			   .bodyToMono(OperationDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<OperationDto> update(OperationDto operationDto, String id) {
		
		return client.post()
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(operationDto)
				   .retrieve()
				   .bodyToMono(OperationDto.class);
	}

}
