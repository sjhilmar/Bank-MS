package com.Bootcamp.BankClient.repository;



import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.Bootcamp.BankClient.domain.Client;

import reactor.core.publisher.Flux;
//@Repository
public interface ClientRepository  extends ReactiveCrudRepository<Client, String> {

	Flux<Client> findByDocumentNumber(String documentNumber);

}
