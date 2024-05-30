package com.webflux.user_service.repository;

import com.webflux.user_service.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    @Modifying
    @Query(value = "update users set balance = balance - :amount where id=:userId " +
            "and balance >= :amount")
    Mono<Boolean> updateUserBalance (UUID userId, double amount);
}
