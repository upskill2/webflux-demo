package com.webflux.customerportfolio.exceptions;

public class InsufficientFundsException extends RuntimeException {

    private static final String MESSAGE = "Customer [id=%d] does not have enough funds";

    public InsufficientFundsException (int customerId) {
        super (MESSAGE.formatted (customerId));
    }
}
