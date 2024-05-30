package com.webflux.user_service.repository;

import com.webflux.user_service.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {

}
