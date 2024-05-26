package com.webflux.microservice.product_service.repository;

import com.webflux.microservice.product_service.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, UUID> {

    @Query ("{ 'price' : { $gte : ?0, $lte : ?1 } }")
    public Flux<Product> priceRange (/*@Param ("min")*/ double min, /*@Param ("max")*/ double max);

    public Flux<Product> findByPriceBetween (Range<Integer> range);

}
