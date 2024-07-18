package com.webflux.microservice.product_service.service;

import com.webflux.microservice.product_service.repository.ProductRepository;
import com.webflux.microservice.product_service.util.ProductMapper;
import com.weblux.demo.commons.dto.productservice.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private Sinks.Many<ProductDTO> sink;

    public Flux<ProductDTO> getAll () {

        return repository
                .findAll ()
                .map (mapper::toProductDto);
    }

    public Mono<ProductDTO> findById (UUID id) {
        return repository
                .findById (id)
                .map (mapper::toProductDto);
    }

    public Mono<ProductDTO> saveProduct (Mono<ProductDTO> productDTO) {
        return productDTO
                .map (mapper::toProductEntity)
                .flatMap (repository::save)
                .map (mapper::toProductDto)
                .doOnNext (n->sink.tryEmitNext (n));
    }

    public Mono<Void> deleteProduct (UUID id) {
        return repository
                .deleteById (
                        id);

    }

    public Flux<ProductDTO> priceRange (final double min, final double max) {
        return repository
                .priceRange (min,max)
                .map (mapper::toProductDto);
    }
}
