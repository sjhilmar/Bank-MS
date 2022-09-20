package com.Bootcamp.BankClient.service.impl;

import org.springframework.stereotype.Service;

import com.Bootcamp.BankClient.domain.Proxy;
import com.Bootcamp.BankClient.repository.ProxyRepository;
import com.Bootcamp.BankClient.service.IProxyService;

import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@NoArgsConstructor
public class ProxyService implements IProxyService {
	
	

    private ProxyRepository proxyRepository;
    
    @Override
    public Flux<Proxy> findAll() throws Exception {
        return proxyRepository.findAll(); 
    }

    @Override
    public Mono<Proxy>  findById(String id) throws Exception {
        return proxyRepository.findById(id).switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Mono<Proxy>  findByFullName(String fullName) throws Exception {
    	return proxyRepository.findProxyByFullName(fullName);
    	
    	
//        Optional<Proxy> proxy = proxyRepository.findProxyByFullName(fullName);
//        if(proxy.isPresent())	return proxyMapper.proxyToProxyModel(proxy.get());
//        else throw new Exception("No se encontraron datos");
    }

    @Override
    public Flux<Proxy> findByClientId(String clientId) throws Exception {
        return proxyRepository.findProxyByClientId(clientId);
    }

    @Override
    public Mono<Proxy> create(Proxy proxyModel) throws Exception {
    	return proxyRepository.findProxyByFullName(proxyModel.getFullName())
    			.switchIfEmpty(proxyRepository.save(proxyModel));
    	
//        if (!proxyRepository.findProxyByFullName(proxyModel.getFullName()).isPresent()){
//            Proxy proxy = proxyRepository.save(proxyMapper.proxyModelToProxy(proxyModel));
//            return proxyMapper.proxyToProxyModel(proxy);
//        }
//        else throw new Exception("El apoderado ya existe");
    }

    @Override
    public Mono<Proxy> update(String id, Proxy proxyModel) throws Exception {
        return proxyRepository.findById(id)
        		.switchIfEmpty(Mono.error(RuntimeException::new))
        		.flatMap(t -> proxyRepository.save(proxyModel) );
    }

    @Override
    public Mono<Void> deleteById(String id) throws Exception {
        return proxyRepository.deleteById(id);
    }
}
