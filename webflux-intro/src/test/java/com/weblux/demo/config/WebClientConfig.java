package com.weblux.demo.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient () {
        return WebClient
                .builder ()
                .filter (this::sessionToken)
                .baseUrl ("http://localhost:8080")
                .build ();
    }


    private Mono<ClientResponse> sessionToken (ClientRequest request, ExchangeFunction function) {
        final ClientRequest clientRequest = request
                .attribute ("auth")
                .map (v -> v.equals ("basic") ? withBasicAuth (request) : withOauth (request))
                .orElse (request);
        //   .orElseThrow ();
        return function.exchange (clientRequest);
    }

    private ClientRequest withBasicAuth (ClientRequest request) {
        return ClientRequest.from (request)
                .headers (h -> h.setBasicAuth ("name", "pwd"))
                .build ();

    }

    private ClientRequest withOauth (ClientRequest request) {

        return ClientRequest.from (request)
                .headers (h -> h.setBearerAuth ("some"))
                .build ();

    }

}
