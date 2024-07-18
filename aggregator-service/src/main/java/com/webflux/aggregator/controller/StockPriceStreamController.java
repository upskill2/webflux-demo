package com.webflux.aggregator.controller;


import com.webflux.aggregator.client.StockServiceClient;
import com.weblux.demo.commons.dto.aggregator.PriceUpdate;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping ("/stock")
@AllArgsConstructor
public class StockPriceStreamController {

    private final StockServiceClient serviceClient;

    @GetMapping (value = "/price-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PriceUpdate> priceUpdateStream () {
        return serviceClient.priceUpdateStream ();

    }

}
