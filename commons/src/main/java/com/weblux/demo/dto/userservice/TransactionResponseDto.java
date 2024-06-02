package com.weblux.demo.dto.userservice;


import java.util.UUID;

public class TransactionResponseDto {
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

    public TransactionsStatus getStatus () {
        return status;
    }

    public void setStatus (TransactionsStatus status) {
        this.status = status;
    }

    private UUID userId;
    private double price;
    private TransactionsStatus status;


    private TransactionResponseDto (Builder builder) {
        this.userId = builder.userId;
        this.price = builder.price;
        this.status = builder.status;
    }

    public TransactionResponseDto () {
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
