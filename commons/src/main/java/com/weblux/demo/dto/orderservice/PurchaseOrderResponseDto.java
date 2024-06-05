package com.weblux.demo.dto.orderservice;

import lombok.Data;

import java.util.UUID;

@Data
public class PurchaseOrderResponseDto {
    private int orderId;
    private UUID productId;
    private UUID userId;
    private double amount;
    private PurchaseOrderStatus status;


}