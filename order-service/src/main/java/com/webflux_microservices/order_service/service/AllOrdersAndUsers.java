package com.webflux_microservices.order_service.service;

import com.webflux_microservices.order_service.client.ProductClient;
import com.webflux_microservices.order_service.client.UserClient;
import com.weblux.demo.commons.dto.orderservice.PurchaseOrderRequestDto;
import com.weblux.demo.commons.dto.orderservice.PurchaseOrderResponseDto;
import com.weblux.demo.commons.dto.orderservice.PurchaseOrderStatus;
import com.weblux.demo.commons.dto.productservice.ProductDTO;
import com.weblux.demo.commons.dto.userservice.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.util.List;

@Service
public class AllOrdersAndUsers {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private OrderFullfilmentService orderFullfilmentService;

    public Flux<PurchaseOrderResponseDto> placeOrdersForAll () {
        final Flux<UserDto> allUsers = userClient.getAllUsers ();
        final Flux<ProductDTO> allProducts = productClient.getAllProducts ();

        final Flux<Tuple2<UserDto, ProductDTO>> zip = Flux.zip (allUsers, allProducts);

        Mono<List<PurchaseOrderRequestDto>> listOfOrders = allUsers
                .flatMap (user -> allProducts
                        .map (product -> new PurchaseOrderRequestDto (user.getUserId (), product.getId (), PurchaseOrderStatus.COMPLETED)))
                .collectList ().subscribeOn (Schedulers.boundedElastic ());

        return listOfOrders.flatMapMany (orders -> Flux.fromIterable (orders)
                .flatMap (n -> orderFullfilmentService.processOrder (Mono.just (n)))
                .subscribeOn (Schedulers.boundedElastic ()));
    }

}
