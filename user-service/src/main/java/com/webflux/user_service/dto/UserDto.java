package com.webflux.user_service.dto;


import java.util.UUID;

public class UserDto {

    private UUID id;
    private String name;
    private double balance;

    private UserDto (Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;

    }

    public UserDto () {
    }

    public UUID getId () {
        return id;
    }

    public void setId (UUID id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public double getBalance () {
        return balance;
    }

    public void setBalance (double balance) {
        this.balance = balance;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private double balance;

        public Builder withId (UUID id) {
            this.id = id;
            return this;
        }

        public Builder withName (String name) {
            this.name = name;
            return this;
        }

        public Builder withBalance (double balance) {
            this.balance = balance;
            return this;
        }

        public UserDto build () {
            return new UserDto (this);
        }
    }
}
