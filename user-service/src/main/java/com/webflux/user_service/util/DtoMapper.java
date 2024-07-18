package com.webflux.user_service.util;

import com.webflux.user_service.entity.User;
import com.webflux.user_service.entity.UserTransaction;
import com.weblux.demo.commons.dto.userservice.TransactionRequestDto;
import com.weblux.demo.commons.dto.userservice.TransactionResponseDto;
import com.weblux.demo.commons.dto.userservice.UserDto;
import org.mapstruct.*;

import java.util.UUID;

@Mapper (componentModel = "spring")
public abstract class DtoMapper {

    public abstract UserDto toUserDto (User user);

    @Mapping (target = "userId", source = "userId", qualifiedByName = "generateId")
    public abstract User toUserEntity (UserDto userDto);

    public abstract UserTransaction toTransactionEntity (TransactionRequestDto transactionRequestDto);

    @Mapping (target = "status", source = "id", qualifiedByName = "accepted")
    public abstract TransactionResponseDto transactionDto (UserTransaction userTransaction);

    @Named ("generateId")
    public UUID transactionStatus (UUID userId) {
        if (userId != null) {
            return userId;
        }
        return UUID.randomUUID ();
    }

    @Named ("accepted")
    public TransactionResponseDto.TransactionsStatus accepted (int id) {
        return  TransactionResponseDto.TransactionsStatus.COMPLETED;
    }

}
