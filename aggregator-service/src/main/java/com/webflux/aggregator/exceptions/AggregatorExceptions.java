package com.webflux.aggregator.exceptions;

import com.weblux.demo.commons.dto.exceptions.CustomerNotFoundException;
import reactor.core.publisher.Mono;

public class AggregatorExceptions {

    public static <T> Mono<T> customerNotFound (int customerId) {
        return Mono.error (new CustomerNotFoundException (customerId));
    }

    public static <T> Mono<T> invalidTradeRequest (String message) {
        return Mono.error (new InvalidTradeException (message));
    }

    public static <T> Mono<T> missingTicker () {
        return Mono.error (new InvalidTradeException ("Ticker is required"));
    }


    public static <T> Mono<T> missingTradeAction () {
        return Mono.error (new InvalidTradeException ("Trade action is required"));
    }

    public static <T> Mono<T> invalidQuantity () {
        return Mono.error (new InvalidTradeException ("Quantity should be grater than zero"));
    }
}
