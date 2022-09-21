package com.Bootcamp.BankProduct.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bootcamp.BankProduct.domain.Product;
import com.Bootcamp.BankProduct.service.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
@Slf4j
public class ProductController {
    @Autowired
    private final IProductService productService;

    @GetMapping()
    @Operation(summary = "Obtener lista de productos")
    public ResponseEntity<Object> getAll() throws Exception {
        Flux<Product> response = productService.findAll();
        log.info("getAll" + "OK");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    @GetMapping(path = { "{id}" }, produces = { "application/json" })
    @Operation(summary = "Obtener productos por ID")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) throws Exception{
        Mono<Product> response = productService.findById(id);
        log.info("getById" + "OK");
        log.debug(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @GetMapping(path = { "/codeProduct" }, produces = { "application/json" })
    @Operation(summary = "Obtener productos por codigo")
    public ResponseEntity<Object> getByCodeProduct(@RequestParam String codeProduct) throws Exception{
        Flux<Product> response = productService.findProductByCodeProduct(codeProduct);
        log.info("getByCodeProduct" + "OK");
        log.debug(codeProduct);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
   
    @GetMapping(path = { "/" }, produces = { "application/json" })
    @Operation(summary = "Obtener productos por descripción")
    public ResponseEntity<Object> getByDescription(@RequestParam String description) throws Exception{
        Mono<Product> response = productService.findProductByDescription(description);
        log.info("getByDescription" + "OK");
        log.debug(description);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    @PostMapping()
    @Operation(summary = "Registrar productos")
    public Mono<Product> create(@RequestBody Product product) throws Exception {
        log.info("create" + "OK");
        log.debug(product.toString());
        return productService.create(product);
    }

    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    @Operation(summary = "Modificar productos")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Product product) throws Exception {
        Mono<Product> response = productService.update(id, product);
        log.info("update" + "OK");
        log.debug(id + "/" + product.toString());
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @DeleteMapping({ "{id}" })
    @Operation(summary = "Eliminar productos por  ID")
    public void deleteById(@PathVariable("id") String id) throws Exception {
        productService.deleteById(id);
    }
}

