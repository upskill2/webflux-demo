package com.webflux.microservice.product_service.controller;

import com.weblux.demo.dto.productservice.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping ("/product-stream")
public class ProductStreamController {

    @Autowired
    private Flux<ProductDTO> dtoFlux;

    @GetMapping (value = "/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDTO> getProductUpdates (@PathVariable double maxPrice) {
        return dtoFlux
                .filter (dto -> dto.getPrice () <= maxPrice);
    }


}
