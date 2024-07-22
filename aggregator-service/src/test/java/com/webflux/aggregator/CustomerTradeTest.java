package com.webflux.aggregator;

import com.weblux.demo.commons.dto.aggregator.PriceUpdate;
import com.weblux.demo.commons.dto.aggregator.TradeRequest;
import com.weblux.demo.commons.dto.domain.Ticker;
import com.weblux.demo.commons.dto.domain.TradeAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import reactor.test.StepVerifier;

import java.io.IOException;

@Slf4j
public class CustomerTradeTest extends BaseIntegrationTest {

    @Test
    public void trade_request_200 () throws IOException {
        String responseBody = resourceToString ("stock-service/stock-price-stream-200.jsonl");

        TradeRequest tradeRequest = new TradeRequest (Ticker.APPL, TradeAction.BUY, 10);

        mockServerClient
                .when (HttpRequest.request ("/stock/price-stream"))
                .respond (
                        HttpResponse.response (responseBody)
                                .withStatusCode (200)
                                .withContentType (MediaType.parse ("application/x-ndjson"))
                );

        webTestClient.post ()
                .uri ("/customers/1")
                .bodyValue (tradeRequest)
                .exchange ()
                .expectStatus ().isOk ()
                .expectBody ()
                .jsonPath ("$.name").isEqualTo ("Test");


    }


}
