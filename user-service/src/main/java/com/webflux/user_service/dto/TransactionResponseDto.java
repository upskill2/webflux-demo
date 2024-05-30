package com.webflux.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionResponseDto {

    private UUID userId;
    private double price;
    private TransactionsStatus status;


    private TransactionResponseDto (Builder builder) {
        this.userId = builder.userId;
        this.price = builder.price;
        this.status = builder.status;
    }


    public static class Builder {
        private UUID userId;
        private double price;
        private TransactionsStatus status;

        public Builder withUserId (UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder withStatus (TransactionsStatus status) {
            this.status = status;
            return this;
        }

        public Builder withPrice (double price) {
            this.price = price;
            return this;
        }

        public TransactionResponseDto build () {
            return new TransactionResponseDto (this);
        }
    }

    public enum TransactionsStatus {
        COMPLETED,
        REJECTED
    }
}
