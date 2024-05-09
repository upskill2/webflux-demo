package com.weblux.demo.controller;

import com.weblux.demo.dto.MultipleRequestDto;
import com.weblux.demo.dto.Response;
import com.weblux.demo.dto.SimpleNotFoundResponse;
import com.weblux.demo.exception.InputValidationException;
import com.weblux.demo.exception.SimpleBadRequestError;
import com.weblux.demo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@RequestMapping ("/reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService service;

    @GetMapping ("/square/{input}")
    public Mono<ResponseEntity<Response>> findSquare (@PathVariable int input) {


/*
        if (input < 10 || input > 20) {
            throw new InputValidationException ("input is less than 10", input);
        }
        return service.findSquare (input);
*/
        return Mono.just (input)
                .handle ((integer, sink) -> {
                    if (integer < 10 || integer > 20) {
                        sink.error (new InputValidationException ("input is less than 10", integer));
                    } else {
                        sink.next (integer);
                    }
                })
                .cast (Integer.class)
                .filter (integer -> integer == 11)
                .flatMap (service::findSquare)
                .map (ResponseEntity::ok)
                .defaultIfEmpty (ResponseEntity.notFound ().build ());
    }

    @GetMapping (value = "/table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTable (@PathVariable int input) {
        return service.multiplicationTable (input);
    }

    @PostMapping ("/multiply")
    public Mono<Response> multiply (@RequestBody Mono<MultipleRequestDto> request,
                                    @RequestHeader Map<String, String> headers) {
        return service.multiply (request);
    }

}
