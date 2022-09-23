package com.bootcamp.java.bankwallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.java.bankwallet.domain.MovementWallet;
import com.bootcamp.java.bankwallet.repository.MovementWalletReposiroty;
import com.bootcamp.java.bankwallet.repository.WalletClientReposiroty;
import com.bootcamp.java.bankwallet.service.IMovementWalletService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class MovementWalletService implements IMovementWalletService {
	@Autowired
	private MovementWalletReposiroty repository;
	@Autowired
	private WalletClientReposiroty clientRepository;
 	
	@Override
	public Flux<MovementWallet> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Mono<MovementWallet> findById(String id) throws Exception {

		return repository.findById(id).switchIfEmpty(Mono.error(new Exception("Movement didn´t find")));
	}

	@Override
	public Mono<MovementWallet> create(String phoneNumber,MovementWallet movement) throws Exception {
	return clientRepository.findAll()
			.filter(t ->t.getPhoneNumber().equalsIgnoreCase(phoneNumber))
			.switchIfEmpty(Mono.error(new Exception("Number Phone doesn't exists")))
			.collectList()
			.flatMap(t -> {
				if (!t.isEmpty()) {
					movement.setWalletClient(t.get(0));
					return repository.save(movement);
				}else {
					return Mono.error(new Exception("No se pudo guardar"));
				}
			});
		
	}

	@Override
	public Mono<MovementWallet> update(String id, MovementWallet movement) throws Exception {

		return repository.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Movement didn´t exists")))
				.flatMap(t -> repository.save(movement) );
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return repository.deleteById(id);
	}

}
