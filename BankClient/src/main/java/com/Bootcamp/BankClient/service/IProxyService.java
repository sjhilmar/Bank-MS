package com.Bootcamp.BankClient.service;

import org.springframework.stereotype.Component;

import com.Bootcamp.BankClient.domain.Proxy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
public interface IProxyService {

    Flux<Proxy> findAll() throws Exception;

    Mono<Proxy> findById(String id) throws Exception;

    Mono<Proxy> findByFullName(String fullName) throws Exception;

    Flux<Proxy> findByClientId(String clientId) throws Exception;

    Mono<Proxy> create(Proxy proxyModel) throws Exception;

    Mono<Proxy> update(String id, Proxy proxyModel) throws Exception;

   Mono<Void> deleteById(String id) throws Exception;



}
