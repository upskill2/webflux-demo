package com.webflux_microservices.order_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequestDto {
   private int userId;
   private UUID productId;

}
