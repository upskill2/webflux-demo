package com.weblux.demo.dto.orderservice;

import lombok.Data;

import java.util.UUID;

@Data
public class PurchaseOrderRequestDto {
   private int userId;
   private UUID productId;

}
