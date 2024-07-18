package com.webflux.aggregator.client;

import com.webflux.aggregator.exceptions.AggregatorExceptions;
import com.weblux.demo.commons.dto.aggregator.TradeRequest;
import com.weblux.demo.commons.dto.customer.CustomerInformation;
import com.weblux.demo.commons.dto.customer.StockTradeRequest;
import com.weblux.demo.commons.dto.customer.StockTradeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;
import org.springframework.web.reactive.function.client.WebClientResponseException.BadRequest;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Slf4j
public class CustomerServiceClient {


    private final WebClient client;

    public CustomerServiceClient (final WebClient client) {
        this.client = client;
    }

    public Mono<CustomerInformation> getCustInfo (int customerID) {
        return client
                .get ()
                .uri ("customers/{customerId}", customerID)
                .retrieve ()
                .bodyToMono (CustomerInformation.class)
                .onErrorResume (NotFound.class, ex -> AggregatorExceptions.customerNotFound (customerID));
    }


    public Mono<StockTradeResponse> trade (int customerId, StockTradeRequest request) {
        return client
                .post ()
                .uri ("customers/{customerId}/trade")
                .bodyValue (request)
                .retrieve ()
                .bodyToMono (StockTradeResponse.class)
                .onErrorResume (NotFound.class, ex -> AggregatorExceptions.customerNotFound (customerId))
                .onErrorResume (BadRequest.class, this::handleException);
    }

    private <T> Mono<T> handleException (BadRequest exception) {
        ProblemDetail problem = exception.getResponseBodyAs (ProblemDetail.class);
        String message = Objects.nonNull (problem) ? problem.getDetail () : exception.getMessage ();
        log.error ("Customer service problem detail: {}, pd");
        return AggregatorExceptions.invalidTradeRequest (message);
    }


}
