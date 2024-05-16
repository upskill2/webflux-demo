package com.weblux.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputFailedValidationResponse {

    private int errorCode;
    private int input;
    private String errorMessage;
}
