package com.Bootcamp.BankClient.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Bootcamp.BankClient.domain.ClientProfile;
import com.Bootcamp.BankClient.web.model.ClientProfileModel;

@Mapper(componentModel = "spring")
public interface ClientProfileMapper {

    ClientProfile clientProfileModelToClientProfile (ClientProfileModel  model);

    ClientProfileModel clientProfileToClientProfileModel (ClientProfile product);

    List<ClientProfileModel> clientProfileToClientProfileModels(List<ClientProfile> product);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget ClientProfile entity, ClientProfileModel updateEntity);

}
