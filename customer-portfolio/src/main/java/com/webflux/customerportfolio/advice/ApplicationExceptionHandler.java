package com.webflux.customerportfolio.advice;

import com.weblux.demo.commons.dto.exceptions.CustomerNotFoundException;
import com.weblux.demo.commons.dto.exceptions.InsufficientFundsException;
import com.weblux.demo.commons.dto.exceptions.InsufficientStocksException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.function.Consumer;

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler (CustomerNotFoundException.class)
    public ProblemDetail handleException (CustomerNotFoundException ex) {
        return build (HttpStatus.NOT_FOUND, ex, problemDetail -> {
            problemDetail.setType (URI.create ("http://localhost.com/problems/customer-not-found"));
            problemDetail.setTitle ("Customer not found");
        });
    }

    @ExceptionHandler (InsufficientFundsException.class)
    public ProblemDetail handleException (InsufficientFundsException ex) {
        return build (HttpStatus.BAD_REQUEST, ex, problemDetail -> {
            problemDetail.setType (URI.create ("http://localhost.com/problems/customer-not-found"));
            problemDetail.setTitle ("Insufficient balance");
        });
    }

    @ExceptionHandler (InsufficientStocksException.class)
    public ProblemDetail handleException (InsufficientStocksException ex) {
        return build (HttpStatus.BAD_REQUEST, ex, problemDetail -> {
            problemDetail.setType (URI.create ("http://localhost.com/problems/customer-not-found"));
            problemDetail.setTitle ("Insufficient shares");
        });
    }


    private ProblemDetail build (HttpStatus httpStatus, Exception ex, Consumer<ProblemDetail> consumer) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail (httpStatus, ex.getMessage ());
        consumer.accept (problemDetail);
        return problemDetail;
    }

}
