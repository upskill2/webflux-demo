package com.webflux.user_service.service;

import com.webflux.user_service.dto.UserDto;
import com.webflux.user_service.repository.UserRepository;
import com.webflux.user_service.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DtoMapper mapper;


    public Flux<UserDto> getAll () {
        return repository
                .findAll ()
                .map (mapper::toUserDto);
    }

    public Mono<UserDto> geyUserById (UUID id) {
        return repository
                .findById (id)
                .map (mapper::toUserDto);
    }

    public Mono<UserDto> saveUser (Mono<UserDto> userDto) {
        return userDto.map (mapper::toUserEntity)
                .flatMap (repository::save)
                .map (mapper::toUserDto);
    }

    public Mono<Void> deleteUser (UUID id) {
        return repository.deleteById (id);
    }


}