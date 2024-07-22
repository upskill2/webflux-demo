package com.webflux.aggregator;

import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;

import java.io.IOException;

public class CustomerInformationTest extends BaseIntegrationTest {

    @Test
    public void customerInformation_200() throws IOException {
        String responseBody = resourceToString ("customer-service/customer-information-200.json");

        mockServerClient
                .when (HttpRequest.request ("/customers/1"))
                .respond (
                        HttpResponse.response (responseBody)
                                .withStatusCode (200)
                                .withContentType (MediaType.APPLICATION_JSON)
                );

        webTestClient.get ()
                .uri ("/customers/1")
                .exchange ()
                .expectStatus ().isOk ()
                .expectBody ()
                .jsonPath ("$.name").isEqualTo ("Test");
    }
    @Test
    public void customerInformation_404() throws IOException {
        String responseBody = resourceToString ("customer-service/customer-information-200.json");

        mockServerClient
                .when (HttpRequest.request ("/customers/1"))
                .respond (
                        HttpResponse.response (responseBody)
                                .withStatusCode (200)
                                .withContentType (MediaType.APPLICATION_JSON)
                );

        webTestClient.get ()
                .uri ("/customers/2")
                .exchange ()
                .expectStatus ().isNotFound ();
    }



}
