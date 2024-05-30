package com.webflux.user_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;


public class UserTransaction {

    @Id
    private int id;
    private UUID userId;
    private double price;
    private LocalDateTime transactionTime;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public UUID getUserId () {
        return userId;
    }

    public void setUserId (UUID userId) {
        this.userId = userId;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public LocalDateTime getTransactionTime () {
        return transactionTime;
    }

    public void setTransactionTime (LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
