package com.weblux.demo.config;

import com.weblux.demo.dto.*;
import com.weblux.demo.exception.DivisionException;
import com.weblux.demo.exception.InputValidationException;
import com.weblux.demo.service.CalculatorService;
import com.weblux.demo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestHandler {

    @Autowired
    private ReactiveMathService service;

    @Autowired
    private CalculatorService calculatorService;

    public Mono<ServerResponse> addNumbers (ServerRequest serverRequest) {
        TwoNumbers two = extractNumbers (serverRequest);
        final CalculatorResponse sum = calculatorService.sum (two.numberOne (), two.numberTwo ());
        return ServerResponse.ok ()
                .contentType (MediaType.APPLICATION_JSON)
                .bodyValue (sum);
    }

    public Mono<ServerResponse> subtractNumbers (ServerRequest serverRequest) {
        TwoNumbers two = extractNumbers (serverRequest);
        final CalculatorResponse subtract = calculatorService.subtract (two.numberOne (), two.numberTwo ());
        return ServerResponse.ok ()
                .bodyValue (subtract);
    }

    public Mono<ServerResponse> multiplyNumbers (ServerRequest serverRequest) {
        TwoNumbers two = extractNumbers (serverRequest);
        return ServerResponse.ok ()
                .bodyValue (calculatorService.multiply (two.numberOne (), two.numberTwo ()));
    }

    public Mono<ServerResponse> divideNumbers (ServerRequest serverRequest) {
        TwoNumbers two = extractNumbers (serverRequest);

        if (two.numberTwo () == 0) {
            return Mono.error (new DivisionException ("Custom exception", 0));
        }

        final CalculatorResponse divide = calculatorService.divide (two.numberOne (), two.numberTwo ());
        return ServerResponse.ok ()
                .bodyValue (divide);
    }

    private TwoNumbers extractNumbers (ServerRequest serverRequest) {
        int numberOne = Integer.parseInt (serverRequest.pathVariable ("numberOne"));
        int numberTwo = Integer.parseInt (serverRequest.pathVariable ("numberTwo"));

        TwoNumbers two = new TwoNumbers (numberOne, numberTwo);
        return two;
    }

    public Mono<ServerResponse> squareHandler (ServerRequest request) {
        int input = Integer.parseInt (request.pathVariable ("input"));

        if (input < 8) {
/*            InputFailedValidationResponse response = new InputFailedValidationResponse ();
            response.setErrorCode (100);
            response.setErrorMessage ("input is less than 10");
            response.setInput (input);*/
            return Mono.error (new InputValidationException ("input is less than 10", input));
        }
        return ServerResponse.ok ().body (service.findSquare (input), Response.class);
    }

    public Mono<ServerResponse> tableHandler (ServerRequest serverRequest) {
        int input = Integer.parseInt (serverRequest.pathVariable ("input"));
        return ServerResponse.ok ().body (service.multiplicationTable (input), Response.class);
    }

    public Mono<ServerResponse> streamTableHandler (ServerRequest serverRequest) {
        int input = Integer.parseInt (serverRequest.pathVariable ("input"));
        return ServerResponse.ok ()
                .contentType (MediaType.TEXT_EVENT_STREAM)
                .body (service.multiplicationTable (input), Response.class);
    }

    public Mono<ServerResponse> multiply (ServerRequest serverRequest) {
        final Mono<MultipleRequestDto> multipleRequestDtoMono = serverRequest.bodyToMono (MultipleRequestDto.class);
        return ServerResponse.ok ()
                .contentType (MediaType.APPLICATION_JSON)
                .body (service.multiply (multipleRequestDtoMono), Response.class);
    }

}
