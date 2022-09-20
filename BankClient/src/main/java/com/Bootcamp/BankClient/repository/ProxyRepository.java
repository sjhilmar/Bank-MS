package com.Bootcamp.BankClient.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.Bootcamp.BankClient.domain.Proxy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface ProxyRepository extends ReactiveCrudRepository<Proxy, String> {

    Mono<Proxy> findProxyByFullName(String fullName);

    Flux<Proxy> findProxyByClientId(String clientId);

}
