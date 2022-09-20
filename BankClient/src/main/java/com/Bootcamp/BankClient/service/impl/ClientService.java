package com.Bootcamp.BankClient.service.impl;

import org.springframework.stereotype.Service;

import com.Bootcamp.BankClient.domain.Client;
import com.Bootcamp.BankClient.repository.ClientRepository;
import com.Bootcamp.BankClient.service.IClientService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
	
	private final ClientRepository clientRepository;
	
	@Override
	public Flux<Client> findAll() throws Exception {
				
		return clientRepository.findAll()
				.switchIfEmpty(Mono.error(RuntimeException::new));				
//		List<Client> clients = clientRepository.findAll();
//		return clientMapper.clientsToClientModels(clients);
	}


	@Override
	public Mono<Client> findById(String id) throws Exception {
		
		return clientRepository.findById(id)
				.switchIfEmpty(Mono.error(RuntimeException::new));
//		Optional<Client> client = clientRepository.findById(id);
//		if (client.isPresent())
//			return clientMapper.clientToClientModel(client.get());
//		else
//			throw new Exception("No se encontraron datos");
	}

	@Override
	public Mono<Client> create(Client client) throws Exception {
		return clientRepository.findByDocumentNumber(client.getDocumentNumber())
				.take(0).singleOrEmpty()
				.switchIfEmpty(clientRepository.save(client));
				
				
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
		return clientRepository.findById(id)
				.switchIfEmpty(Mono.error(RuntimeException::new))
				.flatMap( t -> clientRepository.save(client));
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
