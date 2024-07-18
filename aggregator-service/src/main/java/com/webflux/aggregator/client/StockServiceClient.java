package com.webflux.aggregator.client;

import com.webflux.aggregator.exceptions.AggregatorExceptions;
import com.weblux.demo.commons.dto.aggregator.PriceUpdate;
import com.weblux.demo.commons.dto.aggregator.StockPriceResponse;
import com.weblux.demo.commons.dto.aggregator.TradeRequest;
import com.weblux.demo.commons.dto.customer.StockTradeResponse;
import com.weblux.demo.commons.dto.domain.Ticker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Objects;

@Slf4j
public class StockServiceClient {

    private final WebClient client;
    private Flux<PriceUpdate> flux;

    public StockServiceClient (final WebClient client) {
        this.client = client;
    }

    public Mono<StockPriceResponse> getStockPrice (Ticker ticker) {
        return client
                .get ()
                .uri ("/stock/{ticker}", ticker)
                .retrieve ()
                .bodyToMono (StockPriceResponse.class);
    }

    public Flux<PriceUpdate> priceUpdateStream () {
        if (Objects.isNull (flux)) {
            flux = getPriceUpdates ();
        }
        return flux;
    }


    private Flux<PriceUpdate> getPriceUpdates () {
        return client
                .get ()
                .uri ("/stock/price-stream")
                .accept (MediaType.APPLICATION_NDJSON)
                .retrieve ()
                .bodyToFlux (PriceUpdate.class)
                .retryWhen (retry ())
                .cache (1);
    }

    private Retry retry(){
        return Retry.fixedDelay (100, Duration.ofMillis (100))
                .doBeforeRetry (r ->log.error ("Stock service price stream call failed. retrying... {}",
                        r.failure ().getMessage ()));
    }


}
