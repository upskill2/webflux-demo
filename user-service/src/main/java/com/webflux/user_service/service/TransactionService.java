package com.webflux.user_service.service;


import com.webflux.user_service.entity.UserTransaction;
import com.webflux.user_service.repository.TransactionRepository;
import com.webflux.user_service.repository.UserRepository;
import com.webflux.user_service.util.DtoMapper;
import com.weblux.demo.commons.dto.userservice.TransactionRequestDto;
import com.weblux.demo.commons.dto.userservice.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private DtoMapper mapper;


    public Mono<TransactionResponseDto> createTransaction (TransactionRequestDto request) {
        UUID id = request.getUserId ();
        double amount = request.getPrice ();

        return userRepository
                .updateUserBalance (id, amount)
                .filter (Boolean::booleanValue)
                .map (b -> mapper.toTransactionEntity (request))
                .flatMap (transactionRepository::save)
                .map (mapper::transactionDto)
                .defaultIfEmpty (new TransactionResponseDto
                        .Builder ()
                        .withPrice (amount)
                        .withUserId (id)
                        .withStatus (TransactionResponseDto.TransactionsStatus.REJECTED)
                        .build ());

    }

    public Flux<UserTransaction> getAllTransactions(int id){
        return userRepository.findById(id)
                .flatMapMany(user -> transactionRepository.findAllByUserId(user.getUserId()));
    }


}
