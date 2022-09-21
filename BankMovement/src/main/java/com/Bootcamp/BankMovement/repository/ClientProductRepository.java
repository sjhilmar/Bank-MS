package com.Bootcamp.BankMovement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Bootcamp.BankMovement.domain.ClientProduct;

public interface ClientProductRepository extends MongoRepository<ClientProduct, String> {
    List<ClientProduct> findAllByClientId(String clientId);
    List<ClientProduct> findAllByCodeProduct(String codeProduct);
}
