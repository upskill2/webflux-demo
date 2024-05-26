package com.weblux.demo;


import demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttributesTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    void queryParamsTest () {
        final Mono<Response> responseMono = webClient
                .get ()
                .uri (uriBuilder -> uriBuilder.path ("/reactive-math/square/params")
                        .queryParam ("input", 11)
                        .build ())
                .attribute ("auth", "basic")
                .retrieve ()
                .bodyToMono (Response.class);

        StepVerifier
                .create (responseMono)
                .assertNext (response -> assertEquals (121, response.getOutput ()))
                .as ("test for 11")
                .verifyComplete ();
    }
}
