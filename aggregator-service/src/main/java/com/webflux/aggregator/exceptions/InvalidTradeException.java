package com.webflux.aggregator.exceptions;

public class InvalidTradeException extends RuntimeException {
    public InvalidTradeException (String message) {
        super (message);
    }
}
