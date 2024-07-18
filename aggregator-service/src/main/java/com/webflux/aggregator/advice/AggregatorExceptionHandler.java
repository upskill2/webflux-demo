package com.webflux.aggregator.advice;

import com.webflux.aggregator.exceptions.InvalidTradeException;
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
public class AggregatorExceptionHandler {


    @ExceptionHandler (InvalidTradeException.class)
    public ProblemDetail handleException (InvalidTradeException ex) {
        return build (HttpStatus.NOT_FOUND, ex, problemDetail -> {
            problemDetail.setType (URI.create ("http://localhost.com/problems/invalid-trade-request"));
            problemDetail.setTitle ("Invalid request");
        });
    }

    @ExceptionHandler (CustomerNotFoundException.class)
    public ProblemDetail handleException (CustomerNotFoundException ex) {
        return build (HttpStatus.NOT_FOUND, ex, problemDetail -> {
            problemDetail.setType (URI.create ("http://localhost.com/problems/customer-not-faound"));
            problemDetail.setTitle ("Invalid request");
        });
    }



    private ProblemDetail build (HttpStatus httpStatus, Exception ex, Consumer<ProblemDetail> consumer) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail (httpStatus, ex.getMessage ());
        consumer.accept (problemDetail);
        return problemDetail;
    }

}
