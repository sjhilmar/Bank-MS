package com.Bootcamp.BankClient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankClient.domain.Client;
import com.Bootcamp.BankClient.repository.ClientRepository;
import com.Bootcamp.BankClient.service.IClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class ClientService implements IClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Flux<Client> findAll() throws Exception {

		return clientRepository.findAll().switchIfEmpty(Mono.error(RuntimeException::new));
//		List<Client> clients = clientRepository.findAll();
//		return clientMapper.clientsToClientModels(clients);
	}

	@Override
	public Mono<Client> findById(String id) throws Exception {

		return clientRepository.findById(id).switchIfEmpty(Mono.error(RuntimeException::new));
//		Optional<Client> client = clientRepository.findById(id);
//		if (client.isPresent())
//			return clientMapper.clientToClientModel(client.get());
//		else
//			throw new Exception("No se encontraron datos");
	}

	@Override
	public Mono<Client> create(Client client) throws Exception {
		Flux<Client> var = clientRepository.findAll()
				.filter(t -> t.getDocumentNumber().equals(client.getDocumentNumber()));
		return var.collectList().flatMap(list -> {
			if (!list.isEmpty()) {
				return Mono.error(new Throwable("El cliente ya existe"));
			}
			return clientRepository.save(client);
		});
//		return clientRepository.findByDocumentNumber(client.getDocumentNumber())
//				.take(0).singleOrEmpty()
//				.switchIfEmpty(clientRepository.save(client));

//		List<Client> clients = clientRepository.findByDocumentNumber(clientModel.getDocumentNumber());
//		Client client; 
//		
//		if (clients.isEmpty()) {
//			 client = clientRepository.save(clientMapper.clientModelToClient(clientModel));
//				return clientMapper.clientToClientModel(client);	
//		}else {
//			clientModel.setId("Ya existe el cliente");
//			return clientModel;
//		}

	}

	@Override
	public Mono<Client> update(String id, Client client) throws Exception {
		return clientRepository.findById(id).switchIfEmpty(Mono.error(() -> new Throwable("No existe cliente")))
				.flatMap(t -> clientRepository.save(client));
//		Optional<Client> clientOptional = clientRepository.findById(id);
//
//		if (clientOptional.isPresent()) {
//			Client clientToUpdate = clientOptional.get();
//			clientMapper.update(clientToUpdate, ClientModel);
//			clientRepository.save(clientToUpdate);
//		} else
//			throw new Exception("No se encontraron datos");
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return clientRepository.deleteById(id);
	}

	@Override
	public Flux<Client> findByDocumentNumber(String documentNumber) throws Exception {

		return clientRepository.findByDocumentNumber(documentNumber);
//		List<Client> clients = clientRepository.findByDocumentNumber(documentNumber);
//		return clientMapper.clientsToClientModels(clients);
	}
}
