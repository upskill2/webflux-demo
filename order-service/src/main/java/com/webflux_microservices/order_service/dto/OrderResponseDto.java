package com.webflux_microservices.order_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderResponseDto {
    private int orderId;
    private UUID productId;
    private UUID userId;
    private double amount;
    private OrderStatus status;


}
