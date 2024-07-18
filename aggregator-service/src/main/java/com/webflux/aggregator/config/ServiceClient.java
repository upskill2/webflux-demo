package com.webflux.aggregator.config;

import com.webflux.aggregator.client.CustomerServiceClient;
import com.webflux.aggregator.client.StockServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class ServiceClient {

    @Bean
    public CustomerServiceClient customerServiceClient (@Value ("${customer.service.url}") String url) {
        return new CustomerServiceClient (webClient (url));
    }

    @Bean
    public StockServiceClient stockServiceClient (@Value ("${stock.service.url}") String url) {
        return new StockServiceClient (webClient (url));
    }

    private WebClient webClient (String baseUrl) {
        return WebClient.builder ()
                .baseUrl (baseUrl)
                .build ();
    }

}
