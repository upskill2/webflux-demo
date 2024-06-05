package com.webflux.user_service.controller;


import com.webflux.user_service.entity.UserTransaction;
import com.webflux.user_service.service.TransactionService;
import com.weblux.demo.dto.userservice.TransactionRequestDto;
import com.weblux.demo.dto.userservice.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @GetMapping ("/all")
    public Flux<UserTransaction> getAllTransactions(@RequestParam int id){
        return transactionService.getAllTransactions (id);
    }

}
