package com.weblux.demo.exception;

import lombok.Getter;

import javax.annotation.processing.Generated;

@Getter
public class InputValidationException extends RuntimeException {

    private static final String MSG = "allowed range is 1 to 10";
    private final int ERR_CODE = 999;
    private final int input;

    public InputValidationException (String message, final int input) {
        super (MSG);
        this.input = input;
    }

}
