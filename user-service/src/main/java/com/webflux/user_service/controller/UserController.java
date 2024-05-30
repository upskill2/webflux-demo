package com.webflux.user_service.controller;

import com.webflux.user_service.dto.UserDto;
import com.webflux.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping ("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping ("/all")
    public Flux<UserDto> getAll () {
        return service.getAll ();
    }

    @GetMapping ("/findById/{id}")
    public Mono<ResponseEntity<UserDto>> getUserById (@PathVariable UUID id) {
        return service
                .geyUserById (id)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.notFound ().build ());
    }

    @DeleteMapping ("/delete/{id}")
    public Mono<Void> deleteUser (@PathVariable UUID id) {
        return service.deleteUser (id)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.notFound ().build ())
                .then ();
    }

    @PostMapping ("/save")
    public Mono<ResponseEntity<UserDto>> createUser (@RequestBody Mono<UserDto> userDto) {
        return service.saveUser (userDto)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.badRequest ().build ());
    }

}
