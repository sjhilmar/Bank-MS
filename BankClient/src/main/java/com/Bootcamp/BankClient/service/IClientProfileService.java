package com.Bootcamp.BankClient.service;

import org.springframework.stereotype.Component;

import com.Bootcamp.BankClient.domain.ClientProfile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 @Component
public interface IClientProfileService {

    Flux<ClientProfile> findAll() throws Exception;

    Mono<ClientProfile> findById(String id) throws Exception;

    Mono<ClientProfile> create(ClientProfile clientProfileModel) throws Exception;

    Mono<ClientProfile> update(String id, ClientProfile clientProfileModel) throws Exception;

    Mono<Void> deleteById(String id) throws Exception;

}
