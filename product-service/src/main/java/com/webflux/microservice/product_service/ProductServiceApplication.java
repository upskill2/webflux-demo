package com.webflux.microservice.product_service;

import com.webflux.microservice.product_service.service.ProductService;
import com.weblux.demo.dto.productservice.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ProductServiceApplication {

    @Autowired
    private ProductService productService;

    public static void main (String[] args) {
        SpringApplication.run (ProductServiceApplication.class, args);
    }


    @PostConstruct
    public void init () {

        Flux.just (new ProductDTO.Builder ()
                        .withDescription ("Product 1")
                        .withPrice (99.1)
                        .build ()).concatWith (productStream ())
                .flatMap (productDTO -> productService.saveProduct (Mono.just (productDTO)))
                .subscribe ();
    }

    public Flux<ProductDTO> productStream () {
        return Flux.range (1, 1000)
                .delayElements (Duration.ofMillis (500))
                .map (p -> new ProductDTO ("product-" + p, ThreadLocalRandom.current ().nextInt (10, 100)));
    }


}
