package com.Bootcamp.BankClient.service.impl;

import com.Bootcamp.BankClient.domain.ClientProfile;
import com.Bootcamp.BankClient.web.model.ClientProfileModel;

import java.util.List;

public interface IClientProfileService {

    List<ClientProfileModel> findAll() throws Exception;

    ClientProfileModel findById(String id) throws Exception;

    ClientProfileModel create(ClientProfileModel clientProfileModel) throws Exception;

    void update(String id, ClientProfileModel clientProfileModel) throws Exception;

    void deleteById(String id) throws Exception;

}
