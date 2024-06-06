package com.webflux_microservices.order_service.client;

import com.weblux.demo.dto.userservice.TransactionRequestDto;
import com.weblux.demo.dto.userservice.TransactionResponseDto;
import com.weblux.demo.dto.userservice.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserClient {


    private final WebClient webClient;

    public UserClient (@Value ("${user.service.url}") String url) {
        this.webClient = WebClient
                .builder ()
                .baseUrl (url)
                .build ();
    }


    public Mono<UserDto> getUserById (UUID userId) {

        return webClient.get ()
                .uri ("findById/{id}", userId)
                .retrieve ()
                .bodyToMono (UserDto.class);
    }

    public Flux<UserDto> getAllUsers(){
        return webClient.get ()
                .uri ("/all")
                .retrieve ()
                .bodyToFlux (UserDto.class);
    }

    public Mono<TransactionResponseDto> authorizeTransaction (TransactionRequestDto requestDto) {
     return   webClient.post ()
                .uri ("transaction")
                .bodyValue (requestDto)
                .retrieve ()
                .bodyToMono (TransactionResponseDto.class);

    }



}
