package com.webflux.customerportfolio.controller;

import com.weblux.demo.commons.dto.customer.CustomerInformation;
import com.weblux.demo.commons.dto.customer.StockTradeRequest;
import com.weblux.demo.commons.dto.customer.StockTradeResponse;
import com.webflux.customerportfolio.service.CustomerService;
import com.webflux.customerportfolio.service.TradeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ("customers")
public class CustomerController {

    private final CustomerService customerService;
    private final TradeService tradeService;


    public CustomerController (final CustomerService customerService, final TradeService tradeService) {
        this.customerService = customerService;
        this.tradeService = tradeService;
    }


    @GetMapping ("/{customerId}")
    public Mono<CustomerInformation> getCustomerInfo (@PathVariable int customerId) {
        return customerService.getCustomerInfo (customerId);
    }

    @PostMapping ("/{customerId}")
    public Mono<StockTradeResponse> makeTrade (@RequestBody Mono<StockTradeRequest> stockTradeRequest,
                                               @PathVariable int customerId) {
        return stockTradeRequest.flatMap (req -> tradeService.makeTrade (customerId, req));
    }


}
