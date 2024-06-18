package com.webflux.customerportfolio.controller;

import com.webflux.customerportfolio.dto.CustomerInformation;
import com.webflux.customerportfolio.dto.StockTradeRequest;
import com.webflux.customerportfolio.dto.StockTradeResponse;
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
