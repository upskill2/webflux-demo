package com.weblux.demo.service;

import com.weblux.demo.dto.CalculatorResponse;
import com.weblux.demo.exception.DivisionException;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Service
public class CalculatorService {


    public CalculatorResponse sum (final int numberOne, final int numberTwo) {
        return CalculatorResponse.builder ()
                .numberOne (numberOne)
                .numberTwo (numberTwo)
                .action ("addition")
                .result (numberOne + numberTwo)
                .build ();

    }

    public CalculatorResponse subtract (final int numberOne, final int numberTwo) {
        return CalculatorResponse.builder ()
                .numberOne (numberOne)
                .numberTwo (numberTwo)
                .action ("addition")
                .result (numberOne - numberTwo)
                .build ();

    }


    public CalculatorResponse multiply (final int numberOne, final int numberTwo) {
        return CalculatorResponse.builder ()
                .numberOne (numberOne)
                .numberTwo (numberTwo)
                .action ("addition")
                .result (numberOne * numberTwo)
                .build ();
    }

    public CalculatorResponse divide (final int numberOne, final int numberTwo) {
       // if (numberTwo == 0) throw new DivisionException ("Custom exception");

        return CalculatorResponse.builder ()
                .numberOne (numberOne)
                .numberTwo (numberTwo)
                .action ("addition")
                .result ((double) numberOne / numberTwo)
                .build ();
    }

    public Flux<Integer> sumAll (int first, final List<Integer> numbers) {
        return Flux.range (1, numbers.size ())
                .delayElements (Duration.ofMillis (300))
                .doOnNext (el-> System.out.println ("processing number: " + el))
                .map (i-> i +first);
    }
}
