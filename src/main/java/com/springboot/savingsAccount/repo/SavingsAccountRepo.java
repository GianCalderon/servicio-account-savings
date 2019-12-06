package com.springboot.savingsAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.savingsAccount.document.SavingsAccount;

public interface SavingsAccountRepo extends ReactiveMongoRepository<SavingsAccount, String> {

}
