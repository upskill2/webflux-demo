package com.weblux.demo.commons.dto.productservice;

import java.util.UUID;


public class ProductDTO {
    private UUID id;
    private String description;
    private double price;

    // Constructor that takes a Builder object as an argument
    private ProductDTO (Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.price = builder.price;
    }

    public ProductDTO (UUID id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public ProductDTO (String description, double price) {
        this.description = description;
        this.price = price;
    }

    public ProductDTO () {
    }

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

    // Static nested Builder class
    public static class Builder {
        private UUID id;
        private String description;
        private double price;

        // Setter methods for the builder class that return the builder instance
        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        // Method to build the final ProductDTO object
        public ProductDTO build() {
            return new ProductDTO(this);
        }
    }

}
