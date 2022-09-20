package com.Bootcamp.BankClient.service.impl;

import java.util.List;

import com.Bootcamp.BankClient.web.model.ClientModel;



public interface IClientService {
	List<ClientModel> findAll() throws Exception;

	ClientModel findById(String id) throws Exception;
	
	List<ClientModel>  findByDocumentNumber(String documentNumber) throws Exception;
		
	ClientModel create(ClientModel clientModel) throws Exception;

	void update(String id, ClientModel clientModel) throws Exception;

	void deleteById(String id) throws Exception;
	
}
