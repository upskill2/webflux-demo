package com.webflux.microservice.product_service.controller;

import com.webflux.microservice.product_service.dto.ProductDTO;
import com.webflux.microservice.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping ("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping ("/all")
    public Flux<ProductDTO> all () {
        return service.getAll ();
    }

    @GetMapping ("/priceRange")
    public Flux<ProductDTO> priceRange(@RequestParam double min, @RequestParam double max){
        return service.priceRange(min, max);
                    }

    @PostMapping ("/save")
    public Mono<ResponseEntity<ProductDTO>> saveProduct (@RequestBody Mono<ProductDTO> productDTO) {
        return service.saveProduct (productDTO)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.badRequest ().build ());
    }

    @GetMapping ("/findById/{id}")
    public Mono<ResponseEntity<ProductDTO>> findById (@PathVariable UUID id) {
        return service.findById (id)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.notFound ()
                        .build ());
    }

    @DeleteMapping ("/delete/{id}")
    public Mono<Void> deleteProduct (@PathVariable UUID id) {
        return service.deleteProduct (id);
    }

}
