package com.webflux_microservices.order_service.entity;

import com.weblux.demo.dto.orderservice.PurchaseOrderStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table (name = "purchase_order")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue
    private int id;
    private UUID productId;
    private UUID userId;
    private double amount;
    private PurchaseOrderStatus status;



}
