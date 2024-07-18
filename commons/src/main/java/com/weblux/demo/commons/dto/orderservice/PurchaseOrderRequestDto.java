package com.weblux.demo.commons.dto.orderservice;

import java.util.UUID;

public class PurchaseOrderRequestDto {
    private UUID userId;
    private UUID productId;
    private PurchaseOrderStatus status;

    public PurchaseOrderRequestDto (UUID userId, UUID productId, PurchaseOrderStatus status) {
        this.userId = userId;
        this.productId = productId;
        this.status = status;
    }

    public PurchaseOrderStatus getStatus () {
        return status;
    }

    public void setStatus (PurchaseOrderStatus status) {
        this.status = status;
    }

    public UUID getUserId () {
        return userId;
    }

    public void setUserId (UUID userId) {
        this.userId = userId;
    }

    public UUID getProductId () {
        return productId;
    }

    public void setProductId (UUID productId) {
        this.productId = productId;
    }
}
