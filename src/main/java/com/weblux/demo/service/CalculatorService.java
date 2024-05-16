package com.weblux.demo.service;

import com.weblux.demo.dto.CalculatorResponse;
import com.weblux.demo.exception.DivisionException;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;

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
}
