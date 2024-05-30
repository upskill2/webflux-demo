package com.webflux.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public class TransactionRequestDto {

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

    private UUID userId;
    private double price;

    private TransactionRequestDto (Builder builder) {
        this.userId = builder.userId;
        this.price = builder.price;
    }


    public static class Builder {
        private UUID userId;
        private double price;

        public Builder withUserId (UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder withPrice (double price) {
            this.price = price;
            return this;
        }

        public TransactionRequestDto build () {
            return new TransactionRequestDto (this);
        }


    }

}
