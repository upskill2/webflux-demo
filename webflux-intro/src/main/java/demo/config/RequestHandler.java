package demo.config;


import demo.dto.CalculatorResponse;
import demo.dto.MultipleRequestDto;
import demo.dto.Response;
import demo.dto.TwoNumbers;
import demo.exception.DivisionException;
import demo.exception.InputValidationException;
import demo.service.CalculatorService;
import demo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

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

    public Mono<ServerResponse> calculateWithRange (ServerRequest serverRequest) {

        int first = Integer.parseInt (serverRequest.pathVariable ("numberOne"));

        List<Integer> numbers = serverRequest.queryParam ("numbers")
                .stream ()
                .flatMap (s -> Arrays.stream (s.split (",")))
                .map (Integer::parseInt)
                .toList ();

        return ServerResponse.ok ()
                .contentType (MediaType.TEXT_EVENT_STREAM)
                .body (calculatorService.sumAll (first, numbers), Integer.class);

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
