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

import com.Bootcamp.BankClient.service.IProxyService;
import com.Bootcamp.BankClient.web.model.ProxyModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/proxy")
@Slf4j
public class ProxyController {

    @Autowired
    private final IProxyService proxyService;

    @GetMapping()
    // @Operation(summary = "Get List of Proxies")
    public ResponseEntity<Object> getAll() throws Exception {
        List<ProxyModel> response = proxyService.findAll();
        log.info("getAll" + "OK");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = { "{id}" }, produces = { "application/json" })
    public ResponseEntity<ProxyModel> getById(@PathVariable("id") String id) throws Exception{
        ProxyModel response = proxyService.findById(id);
        log.info("getById" + "OK");
        log.debug(id.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody ProxyModel proxyModel) throws Exception {
        ProxyModel response = proxyService.create(proxyModel);
        log.info("create" + "OK");
        log.debug(proxyModel.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    public void update(@PathVariable("id") String id, @RequestBody ProxyModel proxyModel) throws Exception {
        proxyService.update(id, proxyModel);
        log.info("update" + "OK");
        log.debug(id.toString() + "/" + proxyModel.toString());
    }

    @DeleteMapping({ "{id}" })
    public void deleteById(@PathVariable("id") String id) throws Exception {
        proxyService.deleteById(id);
        log.info("deleteById" + "OK");
        log.debug(id.toString());
    }

    @GetMapping(path = { "byName/{fullName}" }, produces = { "application/json" })
    public ResponseEntity<ProxyModel> getByFullName(@PathVariable("fullName") String fullName) throws Exception{
        ProxyModel response = proxyService.findByFullName(fullName);
        log.info("getByFullName" + "OK");
        log.debug(fullName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("byClientId/{clientId}")
    public ResponseEntity<Object> getProxiesByClientId(String clientId) throws Exception {
        List<ProxyModel> response = proxyService.findByClientId(clientId);
        log.info("getProxiesByClientId" + "OK");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
