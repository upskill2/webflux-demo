package com.webflux.user_service.util;

import com.webflux.user_service.dto.TransactionRequestDto;
import com.webflux.user_service.dto.TransactionResponseDto;
import com.webflux.user_service.dto.UserDto;
import com.webflux.user_service.entity.User;
import com.webflux.user_service.entity.UserTransaction;
import org.mapstruct.*;

import java.util.UUID;

@Mapper (componentModel = "spring")
public abstract class DtoMapper {

    public abstract UserDto toUserDto (User user);

    @Mapping (target = "userId", source = "userId", qualifiedByName = "generateId")
    public abstract User toUserEntity (UserDto userDto);

    public abstract UserTransaction toTransactionEntity (TransactionRequestDto transactionRequestDto);

    @ValueMapping (target = "status", source = "COMPLETED")
    public abstract TransactionResponseDto transactionDto (UserTransaction userTransaction);

    @Named ("generateId")
    public UUID transactionStatus (UUID userId) {
        if (userId != null) {
            return userId;
        }
        return UUID.randomUUID ();
    }

}
