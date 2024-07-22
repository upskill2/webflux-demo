package com.webflux.aggregator;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.client.MockServerClient;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest (properties = {
        "customer.service.url=http://localhost:${mockServerPort}",
        "stock.service.url=http://localhost:${mockServerPort}"
})
@AutoConfigureWebTestClient
@MockServerTest
public abstract class BaseIntegrationTest {

    private final static Path TEST_RESOURCES = Path.of ("src/test/resources/");

    protected MockServerClient mockServerClient;

    @Autowired
    protected WebTestClient webTestClient;

    protected String resourceToString(String relativePath) throws IOException {
        return Files.readString (TEST_RESOURCES.resolve (relativePath));
    }



}


