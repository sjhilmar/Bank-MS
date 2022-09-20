package com.Bootcamp.BankClient.service;

import com.Bootcamp.BankClient.domain.ClientProfile;
import com.Bootcamp.BankClient.repository.ClientProfileRepository;
import com.Bootcamp.BankClient.service.impl.IClientProfileService;
import com.Bootcamp.BankClient.service.mapper.ClientProfileMapper;
import com.Bootcamp.BankClient.web.model.ClientProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientProfileService implements IClientProfileService {

    private final ClientProfileRepository clientProfileRepository;
    private final ClientProfileMapper clientProfileMapper;

    @Override
    public List<ClientProfileModel> findAll() throws Exception {
        List<ClientProfile> clientProfiles = clientProfileRepository.findAll();
        return clientProfileMapper.clientProfileToClientProfileModels(clientProfiles);    }

    @Override
    public ClientProfileModel findById(String id) throws Exception {
        Optional<ClientProfile> clientProfile = clientProfileRepository.findById(id);
        if(clientProfile.isPresent())	return clientProfileMapper.clientProfileToClientProfileModel(clientProfile.get());
        else throw new Exception("No se encontraron datos");
    }

    @Override
    public ClientProfileModel create(ClientProfileModel clientProfileModel) throws Exception {
        ClientProfile clientProfile = clientProfileRepository.save(clientProfileMapper.clientProfileModelToClientProfile(clientProfileModel));
        return clientProfileMapper.clientProfileToClientProfileModel(clientProfile);
    }

    @Override
    public void update(String id, ClientProfileModel clientProfileModel) throws Exception {
        Optional<ClientProfile> clientProfileOptional = clientProfileRepository.findById(id);

        if(clientProfileOptional.isPresent()) {
            ClientProfile clientProfileToUpdate = clientProfileOptional.get();
            clientProfileMapper.update(clientProfileToUpdate, clientProfileModel);
            clientProfileRepository.save(clientProfileToUpdate);
        }
        else throw new Exception("No se encontraron datos");
    }

    @Override
    public void deleteById(String id) throws Exception {
        clientProfileRepository.deleteById(id);
    }
}
