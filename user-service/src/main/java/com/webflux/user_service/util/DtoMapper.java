package com.webflux.user_service.util;

import com.webflux.user_service.dto.TransactionRequestDto;
import com.webflux.user_service.dto.TransactionResponseDto;
import com.webflux.user_service.dto.UserDto;
import com.webflux.user_service.entity.User;
import com.webflux.user_service.entity.UserTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.TransactionStatus.*;

@Mapper (componentModel = "spring")
public abstract class DtoMapper {

    public abstract UserDto toUserDto (User user);

    public abstract User toUserEntity (UserDto userDto);

    public abstract UserTransaction toTransactionEntity (TransactionRequestDto transactionRequestDto);

    @Mapping (target = "status", qualifiedByName = "transactionStatus")
    public abstract TransactionResponseDto transactionDto (UserTransaction userTransaction);

    public static TransactionResponseDto.TransactionsStatus transactionStatus () {
        return TransactionResponseDto.TransactionsStatus.COMPLETED;
    }

}
