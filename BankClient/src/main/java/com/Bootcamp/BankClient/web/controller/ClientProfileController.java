package com.Bootcamp.BankClient.web.controller;

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

import com.Bootcamp.BankClient.domain.ClientProfile;
import com.Bootcamp.BankClient.service.IClientProfileService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/clientProfile")
@Slf4j
public class ClientProfileController {

    private IClientProfileService ClientProfileService;


    /**
     * Get list of ClientProfiles
     * @return
     * @throws Exception
     */
    @GetMapping()
    @Operation(summary = "Get list of ClientProfiles")
    public ResponseEntity<Object> getAll() throws Exception {
        Flux<ClientProfile> response =  ClientProfileService.findAll();
        log.info("getAll" + "OK");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Get ClientProfile by id
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(path = { "{id}" }, produces = { "application/json" })
    public ResponseEntity<Object> getById(@PathVariable("id") String id) throws Exception{
        Mono<ClientProfile>  response = ClientProfileService.findById(id);
        log.info("getById" + "OK");
        log.debug(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Create ClientProfile
     * @param clientProfileModel
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestBody ClientProfile clientProfile) throws Exception {
        try{
            Mono<ClientProfile> response = ClientProfileService.create(clientProfile);
            log.info("create" + "OK");
            log.debug(clientProfile.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity<>("Error:"+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Update ClientProfile by id
     * @param id
     * @param ClientProfileModel
     * @throws Exception
     */
    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    public ResponseEntity<Object> update(  @PathVariable("id") String id, @RequestBody ClientProfile ClientProfile) throws Exception {
        Mono<ClientProfile> response = ClientProfileService.update(id, ClientProfile);
        log.info("update" + "OK");
        log.debug(id.toString()+ "/" + ClientProfile.toString());
        return new ResponseEntity<> (response,HttpStatus.OK);
    }

    /**
     * Delete ClientProfile by id
     * @param id
     * @throws Exception
     * @author aangulom
     */
    @DeleteMapping({ "{id}" })
    public void deleteById(@PathVariable("id") String id) throws Exception {
        ClientProfileService.deleteById(id);
        log.info("deleteById" + "OK");
        log.debug(id.toString());
    }

}
