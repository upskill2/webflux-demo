package com.weblux.demo.controller;

import com.weblux.demo.dto.MultipleRequestDto;
import com.weblux.demo.dto.Response;
import com.weblux.demo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping ("/math")
public class MathController {

    @Autowired
    private MathService mathService;


    @GetMapping ("/square/{input}")
    public Response findSquare (@PathVariable int input) {
        return mathService.findSquare (input);
    }

    @GetMapping ("/table/{input}")
    public List<Response> multiplicationTable (@PathVariable int input) {
        return mathService.multiplicationTable (input);
    }



}
