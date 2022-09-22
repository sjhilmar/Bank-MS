package com.bootcamp.java.bankwallet.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.java.bankwallet.domain.WalletClient;

public interface WalletClientReposiroty extends ReactiveMongoRepository<WalletClient, String>{

}
