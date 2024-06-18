package com.webflux.customerportfolio.repository;

import com.webflux.customerportfolio.domain.Ticker;
import com.webflux.customerportfolio.entity.PortfolioItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PortfolioItemRepository extends ReactiveCrudRepository<PortfolioItem, Integer> {

    Flux<PortfolioItem> findAllByCustomerId (int customerId);
    Mono<PortfolioItem> findByCustomerIdAndTicker(int customerId, Ticker ticker);

}
