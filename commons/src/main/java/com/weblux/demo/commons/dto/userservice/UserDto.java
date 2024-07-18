package com.weblux.demo.commons.dto.userservice;


import java.util.UUID;

public class UserDto {

    private int id;
    private UUID userId;
    private String name;
    private double balance;

    private UserDto (Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;

    }

    public UUID getUserId () {
        return userId;
    }

    public void setUserId (UUID userId) {
        this.userId = userId;
    }

    public UserDto () {
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
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
        private int id;
        private String name;
        private double balance;

        public Builder withId (int id) {
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
