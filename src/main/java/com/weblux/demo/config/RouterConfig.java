package com.weblux.demo.config;

import com.weblux.demo.dto.CalculatorResponse;
import com.weblux.demo.dto.InputFailedValidationResponse;
import com.weblux.demo.exception.DivisionException;
import com.weblux.demo.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    @Autowired
    private RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter () {
        return RouterFunctions
                .route ()
                .path ("/router", this::serverResponseRouterFunction)
                .path ("/calculator/{numberOne}/{numberTwo}", this::calculate)
                .path ("/calculator/{numberOne}", this::calculateRange)
                .build ();
    }

    private RouterFunction<ServerResponse> calculateRange (){
        return RouterFunctions
                .route ()
                .GET (requestHandler::calculateWithRange)
                .build ();
    }

    private RouterFunction<ServerResponse> calculate () {
        return RouterFunctions
                .route ()
                .POST (RequestPredicates.headers (header -> {
                            List<String> op = header.header ("OP");
                            return Objects.equals (op.get (0), "+");
                        }),
                        requestHandler::addNumbers)
                .POST (RequestPredicates.headers (header -> {
                            List<String> op = header.header ("OP");
                            return Objects.equals (op.get (0), "-");
                        }),
                        requestHandler::subtractNumbers)
                .POST (RequestPredicates.headers (header -> {
                            List<String> op = header.header ("OP");
                            return Objects.equals (op.get (0), "*");
                        }),
                        requestHandler::multiplyNumbers)
                .POST (RequestPredicates.headers (header -> {
                            List<String> op = header.header ("OP");
                            return Objects.equals (op.get (0), "/");
                        }),
                        requestHandler::divideNumbers)
                .POST (req -> ServerResponse.badRequest ().bodyValue ("OP should be + - / * "))
                .onError (DivisionException.class, errorHandlerForCalc ())
                .build ();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction () {
        return RouterFunctions
                .route ()
                .GET ("/square/{input}", RequestPredicates.path ("*/?")
                        .or (p -> p.equals (1)), requestHandler::squareHandler)
                .GET ("/square/{input}", req -> ServerResponse.badRequest ().bodyValue ("input should be greater than 10"))
                .GET ("/table/{input}", requestHandler::tableHandler)
                .GET ("/table/{input}/stream", requestHandler::streamTableHandler)
                .POST ("/multiply", requestHandler::multiply)
                .onError (InputValidationException.class, errorHandler ())
                .build ();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> errorHandler () {
        return (throwable, request) -> {
            InputValidationException exception = (InputValidationException) throwable;

            return ServerResponse.badRequest ().bodyValue (
                    InputFailedValidationResponse.builder ()
                            .errorCode (exception.getERR_CODE ())
                            .errorMessage (exception.getMessage ())
                            .input (exception.getInput ())
                            .build ());
        };

    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> errorHandlerForCalc () {
        return (throwable, request) -> {
            DivisionException exception = (DivisionException) throwable;

            return ServerResponse.badRequest ().bodyValue (
                    InputFailedValidationResponse.builder ()
                            .errorMessage (exception.getMessage ())
                            .build ());
        };

    }

}
