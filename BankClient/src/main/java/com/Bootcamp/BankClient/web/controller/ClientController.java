package com.Bootcamp.BankClient.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.Bootcamp.BankClient.service.impl.IClientService;
import com.Bootcamp.BankClient.web.model.ClientModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/client")
@Slf4j
public class ClientController {

	@Autowired
	private final IClientService clientService;
	
	@GetMapping()
	public ResponseEntity<Object> getAll() throws Exception {
		List<ClientModel> response = clientService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	public ResponseEntity<ClientModel> getById(@PathVariable("id") String id) throws Exception {
		ClientModel response = clientService.findById(id);
		log.info("getById" + "OK");
		log.debug(id.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = { "/find/{document}" }, produces = { "application/json" })
	public ResponseEntity<Object> getByNameAndDescription(@PathVariable("document") String document) throws Exception {
		List<ClientModel> response = clientService.findByDocumentNumber(document);
		log.info("getByNameAndDescription" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> create(@RequestBody ClientModel clientModel) throws Exception {
		ClientModel response = clientService.create(clientModel);
		log.info("create" + "OK");
		log.debug(clientModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	public void update(@PathVariable("id") String id, @RequestBody ClientModel clientModel) throws Exception {
		clientService.update(id, clientModel);
		log.info("update" + "OK");
		log.debug(id.toString() + "/" + clientModel.toString());
	}
	@DeleteMapping({ "{id}" })
	public void deleteById(@PathVariable("id") String id) throws Exception {
		clientService.deleteById(id);
		log.info("deleteById" + "OK");
		log.debug(id.toString());
	}
}
