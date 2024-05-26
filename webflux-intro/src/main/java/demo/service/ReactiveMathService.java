package demo.service;

import demo.dto.MultipleRequestDto;
import demo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare (int input) {
        return Mono.fromSupplier (() -> new Response (input * input));
    }

    public Flux<Response> multiplicationTable (int input) {
        return Flux.range (1, 10)
                .delayElements (Duration.ofSeconds (1))
                .doOnNext (i -> {
                    System.out.println ("math-service processing " + i);
                })
                .map (i -> new Response (input * i));
    }

    public Mono<Response> multiply(Mono<MultipleRequestDto> input){
        return input
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }

}
