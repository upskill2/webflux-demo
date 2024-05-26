package com.weblux.demo;


import demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class GetMultiResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;


    @Test
    void testFlux () {
        final Flux<Response> responseFlux = webClient
                .get ()
                .uri ("/reactive-math/table/{input}", 5)
                .retrieve ()
                .bodyToFlux (Response.class);

        StepVerifier
                .create (responseFlux)
                .expectNextCount (10)
                .verifyComplete ();
    }

    @Test
    void testStream () {
        final Flux<Response> responseFlux = webClient
                .get ()
                .uri ("/reactive-math/table/{input}/stream", 5)
                .retrieve ()
                .bodyToFlux (Response.class);

        StepVerifier
                .create (responseFlux)
                .expectNextCount (10)
                .verifyComplete ();
    }

}
