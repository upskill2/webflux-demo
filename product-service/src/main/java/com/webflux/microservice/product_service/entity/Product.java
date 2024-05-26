package com.webflux.microservice.product_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;


import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private UUID id;
    private String description;
    private double price;

    public UUID getId () {
        return id;
    }

    public void setId (UUID id) {
        this.id = id;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }
}
