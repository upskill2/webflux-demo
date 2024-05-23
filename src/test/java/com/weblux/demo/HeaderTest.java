package com.weblux.demo;

import com.weblux.demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.weblux.demo.PostRequestTest.buildMultiplyRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeaderTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    void testHeader () {

        final Mono<Response> responseMono = webClient
                .post ()
                .uri ("/reactive-math/multiply")
                .bodyValue (buildMultiplyRequest (3, 4))
                .headers (header -> header.set ("X-CUSTOM-HEADER", "VALUE"))
                .retrieve ()
                .bodyToMono (Response.class);

        StepVerifier
                .create (responseMono)
                .assertNext (response -> assertEquals (12, response.getOutput ()))
                .expectComplete ();

    }
}
