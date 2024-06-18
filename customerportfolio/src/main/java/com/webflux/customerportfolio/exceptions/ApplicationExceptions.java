package com.webflux.customerportfolio.exceptions;

import reactor.core.publisher.Mono;

public class ApplicationExceptions {

    public static <T> Mono<T> customerNotFound (int customerId) {
        return Mono.error (new CustomerNotFoundException (customerId));
    }

    public static <T> Mono<T> insufficientFunds (int customerId) {
        return Mono.error (new InsufficientFundsException (customerId));
    }

    public static <T> Mono<T> insufficientShares (int customerId) {
        return Mono.error (new InsufficientStocksException (customerId));
    }
}
