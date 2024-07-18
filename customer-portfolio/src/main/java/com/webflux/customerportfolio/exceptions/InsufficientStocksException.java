package com.webflux.customerportfolio.exceptions;

public class InsufficientStocksException extends RuntimeException {

    private static final String MESSAGE = "Customer [id=%d] does not have enough stocks to complete transactions";

    public InsufficientStocksException (int customerId) {
        super (MESSAGE.formatted (customerId));
    }
}
