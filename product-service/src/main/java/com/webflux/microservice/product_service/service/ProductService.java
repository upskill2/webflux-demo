package com.webflux.microservice.product_service.service;

import com.webflux.microservice.product_service.dto.ProductDTO;
import com.webflux.microservice.product_service.entity.Product;
import com.webflux.microservice.product_service.repository.ProductRepository;
import com.webflux.microservice.product_service.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

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
/*        final UUID id = productDTO.map (ProductDTO::getId).block ();
        if (id != null && repository.findById (id).blockOptional ().isPresent ()) {
        }*/

        return productDTO
                .map (mapper::toProductEntity)
                .flatMap (repository::save)
                .map (mapper::toProductDto);
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
