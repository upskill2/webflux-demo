package com.webflux.microservice.product_service.configs;

import com.weblux.demo.commons.dto.productservice.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {

    @Bean
    public Sinks.Many<ProductDTO> sink () {
        return Sinks
                .many ()
                .replay ()
                .limit (1);
    }

    @Bean
    public Flux<ProductDTO> productBroadcast (@Autowired Sinks.Many<ProductDTO> sink) {
        return sink.asFlux ();
    }

}
