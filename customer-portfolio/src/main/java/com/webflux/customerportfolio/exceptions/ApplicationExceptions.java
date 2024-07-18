package com.webflux.customerportfolio.exceptions;

import com.weblux.demo.commons.dto.exceptions.CustomerNotFoundException;
import com.weblux.demo.commons.dto.exceptions.InsufficientFundsException;
import com.weblux.demo.commons.dto.exceptions.InsufficientStocksException;
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
