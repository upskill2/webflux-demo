package com.webflux.customerportfolio.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Customer [id=%d] is not found";

    public CustomerNotFoundException (int customerId) {
        super (MESSAGE.formatted (customerId));
    }

}
