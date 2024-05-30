package com.webflux.user_service.controller;

import com.webflux.user_service.dto.TransactionRequestDto;
import com.webflux.user_service.dto.TransactionResponseDto;
import com.webflux.user_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ("user/transaction")
public class UserTransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Mono<ResponseEntity<TransactionResponseDto>> createTransaction (
            @RequestBody Mono<TransactionRequestDto> transactionRequest) {

        return transactionRequest
                .flatMap (transactionService::createTransaction)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.badRequest ().build ());
    }

}
