package com.weblux.demo.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SimpleNotFoundResponse {

    private int input;
    private final String MSG = "not found";


    public SimpleNotFoundResponse (final int input) {
        this.input = input;
    }
}
