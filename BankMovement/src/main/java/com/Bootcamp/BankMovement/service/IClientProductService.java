package com.Bootcamp.BankMovement.service;

import java.util.List;

import com.Bootcamp.BankMovement.web.model.ClientProductModel;

public interface IClientProductService {


    List<ClientProductModel> findAll() throws Exception;

    ClientProductModel findById(String id) throws Exception;

    ClientProductModel create(ClientProductModel clientProductModel) throws Exception;

    void update(String id, ClientProductModel clientProductModel) throws Exception;

    void deleteById(String id) throws Exception;
}
