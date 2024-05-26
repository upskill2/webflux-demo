package com.webflux.microservice.product_service;

import com.webflux.microservice.product_service.dto.ProductDTO;
import com.webflux.microservice.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProductServiceApplication {

    @Autowired
    private ProductService productService;

    public static void main (String[] args) {
        SpringApplication.run (ProductServiceApplication.class, args);
    }


    @PostConstruct
    public void init () {
        productService.saveProduct (Mono.just (new ProductDTO.Builder ()
                .withDescription ("Product 1")
                .withPrice (99.1)
                .build ())).subscribe ();
    }

}
