package com.Bootcamp.BankClient.web.controller;

import java.util.List;

import com.Bootcamp.BankClient.service.impl.IClientProfileService;
import com.Bootcamp.BankClient.web.model.ClientProfileModel;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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



@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/clientProfile")
@Slf4j
public class ClientProfileController {

    @Autowired
    private final IClientProfileService ClientProfileService;


    /**
     * Get list of ClientProfiles
     * @return
     * @throws Exception
     */
    @GetMapping()
    @Operation(summary = "Get list of ClientProfiles")
    public ResponseEntity<Object> getAll() throws Exception {
        List<ClientProfileModel> response =  ClientProfileService.findAll();
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
    public ResponseEntity<ClientProfileModel> getById(@PathVariable("id") String id) throws Exception{
        ClientProfileModel response = ClientProfileService.findById(id);
        log.info("getById" + "OK");
        log.debug(id.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Create ClientProfile
     * @param clientProfileModel
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestBody ClientProfileModel clientProfileModel) throws Exception {
        try{
            ClientProfileModel response = ClientProfileService.create(clientProfileModel);
            log.info("create" + "OK");
            log.debug(clientProfileModel.toString());
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
    public void update(
            @PathVariable("id") String id,
            @RequestBody ClientProfileModel ClientProfileModel) throws Exception {
        ClientProfileService.update(id, ClientProfileModel);
        log.info("update" + "OK");
        log.debug(id.toString()+ "/" + ClientProfileModel.toString());
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
