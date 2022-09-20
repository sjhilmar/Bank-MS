package com.Bootcamp.BankClient.service.impl;

import com.Bootcamp.BankClient.domain.Proxy;
import com.Bootcamp.BankClient.repository.ProxyRepository;
import com.Bootcamp.BankClient.service.IProxyService;
import com.Bootcamp.BankClient.service.mapper.ProxyMapper;
import com.Bootcamp.BankClient.web.model.ProxyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProxyService implements IProxyService {

    private final ProxyRepository proxyRepository;
    private final ProxyMapper proxyMapper;
    @Override
    public List<ProxyModel> findAll() throws Exception {
        List<Proxy> proxies = proxyRepository.findAll();
        return proxyMapper.proxyToProxyModels(proxies);
    }

    @Override
    public ProxyModel findById(String id) throws Exception {
        Optional<Proxy> proxy = proxyRepository.findById(id);
        if(proxy.isPresent())	return proxyMapper.proxyToProxyModel(proxy.get());
        else throw new Exception("No se encontraron datos");
    }

    @Override
    public ProxyModel findByFullName(String fullName) throws Exception {
        Optional<Proxy> proxy = proxyRepository.findProxyByFullName(fullName);
        if(proxy.isPresent())	return proxyMapper.proxyToProxyModel(proxy.get());
        else throw new Exception("No se encontraron datos");
    }

    @Override
    public List<ProxyModel> findByClientId(String clientId) throws Exception {
        List<Proxy> proxies = proxyRepository.findProxyByClientId(clientId);
        return proxyMapper.proxyToProxyModels(proxies);
    }

    @Override
    public ProxyModel create(ProxyModel proxyModel) throws Exception {
        if (!proxyRepository.findProxyByFullName(proxyModel.getFullName()).isPresent()){
            Proxy proxy = proxyRepository.save(proxyMapper.proxyModelToProxy(proxyModel));
            return proxyMapper.proxyToProxyModel(proxy);
        }
        else throw new Exception("El apoderado ya existe");
    }

    @Override
    public void update(String id, ProxyModel proxyModel) throws Exception {
        Optional<Proxy> proxyOptional = proxyRepository.findById(id);
        if(proxyOptional.isPresent()) {
            Proxy proxyToUpdate = proxyOptional.get();
            proxyMapper.update(proxyToUpdate, proxyModel);
            proxyRepository.save(proxyToUpdate);
        }
        else throw new Exception("No se encontraron datos");
    }

    @Override
    public void deleteById(String id) throws Exception {
        proxyRepository.deleteById(id);
    }
}
