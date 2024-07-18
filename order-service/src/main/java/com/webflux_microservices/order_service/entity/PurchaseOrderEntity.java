package com.webflux_microservices.order_service.entity;

import com.weblux.demo.commons.dto.orderservice.PurchaseOrderStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table (name = "purchase_order")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue
    private int id;
    private UUID productId;
    private UUID userId;
    private double amount;
    private PurchaseOrderStatus status;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
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
