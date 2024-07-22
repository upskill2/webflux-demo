package com.webflux.aggregator;

import com.weblux.demo.commons.dto.aggregator.PriceUpdate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import reactor.test.StepVerifier;

import java.io.IOException;

@Slf4j
public class StockServiceTest extends BaseIntegrationTest {

    @Test
    public void customerInformation_200 () throws IOException {
        String responseBody = resourceToString ("stock-service/stock-price-stream-200.jsonl");

        mockServerClient
                .when (HttpRequest.request ("/stock/price-stream"))
                .respond (
                        HttpResponse.response (responseBody)
                                .withStatusCode (200)
                                .withContentType (MediaType.parse ("application/x-ndjson"))
                );

        webTestClient.get ()
                .uri ("/stock/price-stream")
                .accept (org.springframework.http.MediaType.TEXT_EVENT_STREAM)
                .exchange ()
                .expectStatus ().isOk ()
                .returnResult (PriceUpdate.class)
                .getResponseBody ()
                .doOnNext (n-> log.info ("{}", n.price ()))
                .as (StepVerifier::create)
                .assertNext (p-> Assertions.assertEquals (53, p.price ()))
                .assertNext (p-> Assertions.assertEquals (54, p.price ()))
                .assertNext (p-> Assertions.assertEquals (55, p.price ()))
                .expectComplete ()
                .verify ();


    }


}
