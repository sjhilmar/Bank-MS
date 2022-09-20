package com.Bootcamp.BankClient.service;

import com.Bootcamp.BankClient.web.model.ProxyModel;

import java.util.List;

public interface IProxyService {

    List<ProxyModel> findAll() throws Exception;

    ProxyModel findById(String id) throws Exception;

    ProxyModel findByFullName(String fullName) throws Exception;

    List<ProxyModel> findByClientId(String clientId) throws Exception;

    ProxyModel create(ProxyModel proxyModel) throws Exception;

    void update(String id, ProxyModel proxyModel) throws Exception;

    void deleteById(String id) throws Exception;



}
