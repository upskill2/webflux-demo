package com.weblux.demo.dto.orderservice;

import java.util.UUID;

public class PurchaseOrderResponseDto {
    private int orderId;
    private UUID productId;
    private UUID userId;
    private double amount;
    private PurchaseOrderStatus status;

    public int getOrderId () {
        return orderId;
    }

    public void setOrderId (int orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId () {
        return productId;
    }

    public void setProductId (UUID productId) {
        this.productId = productId;
    }

    public UUID getUserId () {
        return userId;
    }

    public void setUserId (UUID userId) {
        this.userId = userId;
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount (double amount) {
        this.amount = amount;
    }

    public PurchaseOrderStatus getStatus () {
        return status;
    }

    public void setStatus (PurchaseOrderStatus status) {
        this.status = status;
    }
}
