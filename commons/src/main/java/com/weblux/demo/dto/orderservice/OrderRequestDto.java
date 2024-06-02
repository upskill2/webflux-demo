package com.weblux.demo.dto.orderservice;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequestDto {
   private int userId;
   private UUID productId;

}
