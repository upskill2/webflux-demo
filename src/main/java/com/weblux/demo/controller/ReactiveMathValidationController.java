package com.weblux.demo.controller;

import com.weblux.demo.dto.Response;
import com.weblux.demo.exception.InputValidationException;
import com.weblux.demo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ("/reactive-math")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService service;

    @GetMapping ("/square/{input}/throw")
    public Mono<Response> findSquareThrow (@PathVariable int input) {

       if(input <10 || input > 20) {
           throw new InputValidationException ("input is less than 10", input);}

        return service.findSquare (input);

    }
}
