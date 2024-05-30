package com.webflux.user_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserTransaction {

    @Id
    private int id;
    private UUID userId;
    private double price;
    private LocalDateTime transactionTime;

}
