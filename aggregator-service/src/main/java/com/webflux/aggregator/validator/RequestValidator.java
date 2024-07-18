package com.webflux.aggregator.validator;

import com.webflux.aggregator.exceptions.AggregatorExceptions;
import com.weblux.demo.commons.dto.aggregator.TradeRequest;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class RequestValidator {

    private static Predicate<TradeRequest> hasTicker () {
        return dto -> Objects.nonNull (dto.ticker ());
    }

    private static Predicate<TradeRequest> isValidAction () {
        return dto -> Objects.nonNull (dto.action ());
    }

    private static Predicate<TradeRequest> isValidQuantity () {
        return dto -> Objects.nonNull (dto.quantity ()) && dto.quantity () > 0;
    }

    public static UnaryOperator<Mono<TradeRequest>> validate () {
            return mono ->mono.filter (hasTicker ())
                    .switchIfEmpty (AggregatorExceptions.missingTicker ())
                    .filter (isValidAction ())
                    .switchIfEmpty (AggregatorExceptions.missingTradeAction ())
                    .filter (isValidQuantity ())
                    .switchIfEmpty (AggregatorExceptions.invalidQuantity ());
    }


}
