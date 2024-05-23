package com.weblux.demo;

import com.weblux.demo.dto.Response;
import com.weblux.demo.exception.InputValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.xml.bind.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadRequestTest extends BaseTest {

    @Autowired
    private WebClient webClient;


    @Test
    void badRequestRetrieveTest () {
        final Mono<Response> responseMono = webClient
                .get ()
                .uri ("/reactive-math/square/{input}", 8)
                .retrieve ()
                .bodyToMono (Response.class);

        StepVerifier
                .create (responseMono)
                .expectNextCount (0)
                .expectError (UnknownHttpStatusCodeException.class);
    }

    @Test
    void badRequestTestExchange () {
        final Mono<Response> responseMono = webClient
                .get ()
                .uri ("/reactive-math/square/{input}", 8)
                .exchangeToMono (clientResponse -> {

                    if (clientResponse.rawStatusCode () == 999) {
                        return clientResponse.createException ()
                                .flatMap (Mono::error);
                    } else {
                        return clientResponse.bodyToMono (Response.class);
                    }
                });

        StepVerifier
                .create (responseMono)
                .expectNextCount (0)
                .as ("test for 8")
                .expectError (InputValidationException.class);
    }
}
