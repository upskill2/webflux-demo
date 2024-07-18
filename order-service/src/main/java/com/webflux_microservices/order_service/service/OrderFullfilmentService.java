package com.webflux_microservices.order_service.service;

import com.webflux_microservices.order_service.client.ProductClient;
import com.webflux_microservices.order_service.client.UserClient;
import com.webflux_microservices.order_service.repository.PurchaseOrderRepository;
import com.webflux_microservices.order_service.util.ContextMapper;
import com.weblux.demo.commons.dto.orderservice.PurchaseOrderRequestDto;
import com.weblux.demo.commons.dto.orderservice.PurchaseOrderResponseDto;
import com.weblux.demo.commons.dto.orderservice.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.UUID;

@Service
public class OrderFullfilmentService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ContextMapper mapper;
    @Autowired
    private PurchaseOrderRepository repository;

    public Mono<PurchaseOrderResponseDto> processOrder (Mono<PurchaseOrderRequestDto> requestDto) {

        return requestDto.map (dto -> new RequestContext (dto))
                .flatMap (this::productRequestResponse)
                .doOnNext (context -> {
                    context.setTransactionRequestDto (mapper.mapToTransactionRequestDto (context));
                })
                .flatMap (this::userRequestResponse)
                .map (ctx -> {
                    ctx.setPurchaseOrderRequestDto (mapper.mapToPurchaseOrderRequest (ctx));
                    return ctx;
                }).map (m -> repository.save (mapper.contextToPurchaseEntity (m)))
                .map (mapper::purchaseEntityToPurchaseResponse)
                .subscribeOn (Schedulers.boundedElastic ());
    }

    public Flux<PurchaseOrderResponseDto> getAllOrders (UUID userId) {
        return Flux
                .fromStream (() -> repository
                        .findByUserId (userId)
                        .stream ()
                        .map (mapper::purchaseEntityToPurchaseResponse))
                .subscribeOn (Schedulers.boundedElastic ());
    }


    private Mono<RequestContext> productRequestResponse (RequestContext context) {
        return productClient.getProductById (context.getPurchaseOrderRequestDto ().getProductId ())
                .doOnNext (context::setProductDTO)
                .retryWhen ( Retry.fixedDelay (5, Duration.ofMillis (500)))
                .thenReturn (context);
    }

    private Mono<RequestContext> userRequestResponse (RequestContext context) {
        return userClient.authorizeTransaction (context.getTransactionRequestDto ())
                .doOnNext (context::setTransactionResponseDto)
                .retryWhen ( Retry.fixedDelay (5, Duration.ofMillis (500)))
                .thenReturn (context);

    }

}
