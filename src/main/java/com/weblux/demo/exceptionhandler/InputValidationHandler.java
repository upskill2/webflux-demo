package com.weblux.demo.exceptionhandler;

import com.weblux.demo.dto.InputFailedValidationResponse;
import com.weblux.demo.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleInputValidationException(InputValidationException ex) {

        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getERR_CODE());
        response.setInput(ex.getInput());
        response.setErrorMessage(ex.getMessage());


        return ResponseEntity
                .status (ex.getERR_CODE ())
                .body(response);
    }


}
