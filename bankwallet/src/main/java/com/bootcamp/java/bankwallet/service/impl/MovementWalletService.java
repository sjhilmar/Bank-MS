package com.bootcamp.java.bankwallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.java.bankwallet.domain.MovementWallet;
import com.bootcamp.java.bankwallet.repository.MovementWalletReposiroty;
import com.bootcamp.java.bankwallet.service.IMovementWalletService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class MovementWalletService implements IMovementWalletService {
	@Autowired
	private MovementWalletReposiroty repository;
 	
	@Override
	public Flux<MovementWallet> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Mono<MovementWallet> findById(String id) throws Exception {

		return repository.findById(id).switchIfEmpty(Mono.error(new Exception("Client didn´t find")));
	}

	@Override
	public Mono<MovementWallet> create(MovementWallet movement) throws Exception {
		Flux<MovementWallet> valida = repository.findAll()
				.filter(t -> t.getMovementId().equals(movement.getMovementId() ));
		return valida.collectList()
				.flatMap(t ->{
					if(!t.isEmpty()) return Mono.error(new Exception("Client already exists"));
					return repository.save(movement);
				});
		
	}

	@Override
	public Mono<MovementWallet> update(String id, MovementWallet movement) throws Exception {

		return repository.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Client didn´t exists")))
				.flatMap(t -> repository.save(movement) );
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return repository.deleteById(id);
	}

}
