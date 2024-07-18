package com.webflux.customerportfolio.service;

import com.webflux.customerportfolio.dto.StockTradeRequest;
import com.webflux.customerportfolio.dto.StockTradeResponse;
import com.webflux.customerportfolio.entity.Customer;
import com.webflux.customerportfolio.entity.PortfolioItem;
import com.webflux.customerportfolio.exceptions.ApplicationExceptions;
import com.webflux.customerportfolio.mapper.DtoMapper;
import com.webflux.customerportfolio.repository.CustomerRepository;
import com.webflux.customerportfolio.repository.PortfolioItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class TradeService {

    private final CustomerRepository customerRepository;
    private final PortfolioItemRepository portfolioItemRepository;
    private final DtoMapper dtoMapper;

    public TradeService (final CustomerRepository customerRepository, final PortfolioItemRepository portfolioItemRepository, final DtoMapper dtoMapper) {
        this.customerRepository = customerRepository;
        this.portfolioItemRepository = portfolioItemRepository;
        this.dtoMapper = dtoMapper;
    }


    @Transactional
    public Mono<StockTradeResponse> makeTrade (int customerId, StockTradeRequest request) {

        return switch (request.action ()) {
            case BUY -> buyStock (customerId, request);
            case SELL -> sellStock (customerId, request);
        };
    }

    private Mono<StockTradeResponse> buyStock (final int customerId, final StockTradeRequest request) {

        final Mono<Customer> customerMono = customerRepository
                .findById (customerId)
                .switchIfEmpty (ApplicationExceptions.customerNotFound (customerId))
                .filter (customer -> customer.getBalance () >= request.calculateTotalPrice ())
                .switchIfEmpty (ApplicationExceptions.insufficientFunds (customerId));

        final Mono<PortfolioItem> portfolioItemMono = portfolioItemRepository
                .findByCustomerIdAndTicker (customerId, request.ticker ())
                .defaultIfEmpty (new PortfolioItem (customerId, request.ticker (), 0));

        return customerMono.zipWhen (customer -> portfolioItemMono)
                .flatMap (tuple -> executeBuy (tuple.getT1 (), tuple.getT2 (), request));
    }

    private Mono<StockTradeResponse> executeBuy (Customer customer, PortfolioItem portfolioItem, StockTradeRequest stockTradeRequest) {
        customer.setBalance (customer.getBalance () - stockTradeRequest.calculateTotalPrice ());
        portfolioItem.setQuantity (portfolioItem.getQuantity () + stockTradeRequest.quantity ());

        return saveResponse (customer, portfolioItem, stockTradeRequest);

    }

    private Mono<StockTradeResponse> sellStock (final int customerId, final StockTradeRequest request) {

        final Mono<Customer> customerMono = customerRepository
                .findById (customerId)
                .switchIfEmpty (ApplicationExceptions.customerNotFound (customerId));

        final Mono<PortfolioItem> portfolioItemMono = portfolioItemRepository
                .findByCustomerIdAndTicker (customerId, request.ticker ())
                .filter (portfolioItem -> portfolioItem.getQuantity () >= request.quantity ())
                .switchIfEmpty (ApplicationExceptions.insufficientShares (customerId));


        return customerMono.zipWith (portfolioItemMono)
                .flatMap (tuple -> executeSell (tuple.getT1 (), tuple.getT2 (), request));
    }

    private Mono<? extends StockTradeResponse> executeSell (final Customer customer, final PortfolioItem portfolioItem,
                                                            final StockTradeRequest stockTradeRequest) {
        customer.setBalance (customer.getBalance () + stockTradeRequest.calculateTotalPrice ());
        portfolioItem.setQuantity (portfolioItem.getQuantity () - stockTradeRequest.quantity ());

        return saveResponse (customer, portfolioItem, stockTradeRequest);

    }

    private Mono<StockTradeResponse> saveResponse (final Customer customer, final PortfolioItem portfolioItem, final StockTradeRequest stockTradeRequest) {
        return Mono.zip (customerRepository.save (customer), portfolioItemRepository.save (portfolioItem))
                .map (tuple -> dtoMapper.toStockTradeResponse (stockTradeRequest, customer.getId (),
                        (int) tuple.getT1 ().getBalance ()));
    }

}
