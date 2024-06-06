package com.webflux_microservices.order_service.controller;

import com.webflux_microservices.order_service.service.AllOrdersAndUsers;
import com.webflux_microservices.order_service.service.OrderFullfilmentService;
import com.weblux.demo.dto.orderservice.PurchaseOrderRequestDto;
import com.weblux.demo.dto.orderservice.PurchaseOrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping ("order")
public class PurchaseOrderController {

    @Autowired
    private OrderFullfilmentService service;
    @Autowired
    private AllOrdersAndUsers allOrdersAndUsers;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDto>> processOrder (@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
        return service.processOrder (requestDtoMono)
                .map (ResponseEntity::ok)
                .onErrorReturn (WebClientResponseException.class, ResponseEntity.badRequest ().build ())
                .onErrorReturn (WebClientRequestException.class, ResponseEntity.status (HttpStatus.SERVICE_UNAVAILABLE).build ());
    }

    @GetMapping ("/allByUserId/{userId}")
    public Flux<PurchaseOrderResponseDto> getAllOrders (@PathVariable UUID userId) {
        return service.getAllOrders (userId);
    }

    @GetMapping ("/processAll")
    public Flux<PurchaseOrderResponseDto> processAll () {
        return allOrdersAndUsers.placeOrdersForAll ();
    }


}
