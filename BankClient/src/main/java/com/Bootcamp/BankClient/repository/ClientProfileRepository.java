package com.Bootcamp.BankClient.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.Bootcamp.BankClient.domain.ClientProfile;
//@Repository
public interface ClientProfileRepository extends ReactiveCrudRepository<ClientProfile, String> {
	

}
