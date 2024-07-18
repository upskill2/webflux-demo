package com.webflux.aggregator.controller;

import com.webflux.aggregator.service.CustomerPortfolioService;
import com.webflux.aggregator.validator.RequestValidator;
import com.weblux.demo.commons.dto.aggregator.TradeRequest;
import com.weblux.demo.commons.dto.customer.CustomerInformation;
import com.weblux.demo.commons.dto.customer.StockTradeRequest;
import com.weblux.demo.commons.dto.customer.StockTradeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ("/customers")
@AllArgsConstructor
public class CustomerPortfolioController {

    private final CustomerPortfolioService service;


    @GetMapping ("/{customerId}")
    public Mono<CustomerInformation> getCustInformation (@PathVariable int customerId) {
        return service.getCustomerInfo (customerId);
    }

    @PostMapping ("/{customerId}/trade")
    public Mono<StockTradeResponse> trade (@PathVariable int customerID, @RequestBody Mono<TradeRequest> request) {
        return request
                .transform (RequestValidator.validate ())
                .flatMap (req -> service.trade (customerID, req));
    }

}
