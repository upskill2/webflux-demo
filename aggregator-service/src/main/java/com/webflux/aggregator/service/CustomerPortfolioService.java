package com.webflux.aggregator.service;

import com.webflux.aggregator.client.CustomerServiceClient;
import com.webflux.aggregator.client.StockServiceClient;
import com.weblux.demo.commons.dto.aggregator.TradeRequest;
import com.weblux.demo.commons.dto.customer.CustomerInformation;
import com.weblux.demo.commons.dto.customer.StockTradeRequest;
import com.weblux.demo.commons.dto.customer.StockTradeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerPortfolioService {

    private final CustomerServiceClient customerServiceClient;
    private final StockServiceClient stockServiceClient;

    public Mono<CustomerInformation> getCustomerInfo (int customerId) {
        return customerServiceClient.getCustInfo (customerId);
    }

    public Mono<StockTradeResponse> trade (int customerId, TradeRequest trade) {
        return stockServiceClient.getStockPrice (trade.ticker ())
                .map (p -> p.price ())
                .map (p -> this.toStockTradeRequest (p, trade))
                .flatMap (req -> customerServiceClient.trade (customerId, req));

    }

    private StockTradeRequest toStockTradeRequest (double price, TradeRequest trade) {
        return new StockTradeRequest (trade.ticker (), price, trade.quantity (), trade.action ());
    }

}
