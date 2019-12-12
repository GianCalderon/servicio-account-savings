package com.springboot.savingsAccount.document;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection = "SavingsAccount")
public class SavingsAccount {
	
	@Id
	private String id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String numberAccount;
	
	@NotBlank
	private Double tea;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private int balance;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	

}
