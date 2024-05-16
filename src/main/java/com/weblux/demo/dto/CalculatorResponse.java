package com.weblux.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculatorResponse {

    private int numberOne;
    private int numberTwo;
    private String action;
    private double result;
}
