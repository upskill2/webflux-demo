package demo.service;


import demo.dto.Response;
import demo.util.SleepUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class MathService {

    public Response findSquare (int input) {
        return new Response (input * input);
    }

    public List<Response> multiplicationTable (int input) {
        return IntStream.rangeClosed (1, 10)
                .peek (i -> {
                    System.out.println ("math-service processing " + i);
                    SleepUtil.sleepSeconds (1)
                    ;
                })
                .mapToObj (i -> new Response (input * i))
                .toList ();
    }

}
