package com.webflux.customerportfolio.repository;

import com.webflux.customerportfolio.dto.CustomerInformation;
import com.webflux.customerportfolio.entity.Customer;
import lombok.NoArgsConstructor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
