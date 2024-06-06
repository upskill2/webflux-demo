package com.webflux_microservices.order_service.client;

import com.weblux.demo.dto.productservice.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient (@Value ("${product.service.url}") String url) {
        this.webClient = WebClient.builder ()
                .baseUrl (url)
                .build ();
    }

    public Mono<ProductDTO> getProductById (UUID productId) {
        return webClient
                .get ()
                .uri ("findById/{id}", productId)
                .retrieve ()
                .bodyToMono (ProductDTO.class);

    }

    public Flux<ProductDTO> getAllProducts () {
        return webClient
                .get ()
                .uri ("/all")
                .retrieve ()
                .bodyToFlux (ProductDTO.class);
    }
}
