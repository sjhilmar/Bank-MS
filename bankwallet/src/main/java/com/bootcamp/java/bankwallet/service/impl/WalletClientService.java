package com.bootcamp.java.bankwallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.java.bankwallet.domain.WalletClient;
import com.bootcamp.java.bankwallet.repository.WalletClientReposiroty;
import com.bootcamp.java.bankwallet.service.IWalletClientService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class WalletClientService implements IWalletClientService {
	@Autowired
	private WalletClientReposiroty repository;

	@Override
	public Flux<WalletClient> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Mono<WalletClient> findById(String id) throws Exception {

		return repository.findById(id).switchIfEmpty(Mono.error(() ->  new Throwable("Data not exists")));
	}

	@Override
	public Mono<WalletClient> create(WalletClient client) throws Exception {
		
			Flux<WalletClient>valida = repository.findAll().filter(t ->t.getId().equalsIgnoreCase(client.getId()) );
			return valida.collectList().flatMap(t -> {
				if (!t.isEmpty()) return  Mono.error(new Exception("Client exist"));
				return repository.save(client);
			});
		
	}

	@Override
	public Mono<WalletClient> update(String id, WalletClient client) throws Exception {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Client not exist")))
				.flatMap(t ->repository.save(client));
		
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {

		return repository.deleteById(id);
		
	}

}
