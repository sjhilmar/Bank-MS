package com.Bootcamp.BankClient.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bootcamp.BankClient.domain.Client;
import com.Bootcamp.BankClient.service.IClientService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@NoArgsConstructor
@RequestMapping("/v3/client")
@Slf4j
public class ClientController {
	
	private IClientService clientService;
	
	@GetMapping()
	public ResponseEntity<Object> getAll() throws Exception {
		Flux<Client> response = clientService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	
	}
	
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	public ResponseEntity<Object> findById(@PathVariable("id") String id) throws Exception {
		Mono<Client> response = clientService.findById(id);
		log.info("getById" + "OK");
		log.debug(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = { "/find/{document}" }, produces = { "application/json" })
	public ResponseEntity<Object> getByNameAndDescription(@PathVariable("document") String document) throws Exception {
		Flux<Client> response = clientService.findByDocumentNumber(document);
		log.info("getByNameAndDescription" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> create(@RequestBody Client client) throws Exception {
		Mono<Client> response = clientService.create(client);
		log.info("create" + "OK");
		log.debug(client.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Client client) throws Exception {
		Mono<Client> response= clientService.update(id, client);
		log.info("update" + "OK");
		log.debug(id + "/" + client.toString());
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	@DeleteMapping({ "{id}" })
	public void deleteById(@PathVariable("id") String id) throws Exception {
		clientService.deleteById(id);
		log.info("deleteById OK");
		log.debug(id);
	}
}
