package com.weblux.demo;

import com.weblux.demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetSingleResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;


    @Test
    void blockTest () {
        final Response response = webClient
                .get ()
                .uri ("/reactive-math/square/{input}", 11)
                .retrieve ()
                .bodyToMono (Response.class)
                .block ();


        assert response != null;
        assertEquals (121, response.getOutput ());
    }

    @Test
    void stepVerifierTest () {
        final Flux<Response> monoresponse = webClient
                .get ()
                .uri ("/reactive-math/square/{input}", 11)
                .retrieve ()
                .bodyToFlux (Response.class);

        StepVerifier
                .create (monoresponse)
                .assertNext (response -> assertEquals (121, response.getOutput ()))
                .verifyComplete ();

    }




}
