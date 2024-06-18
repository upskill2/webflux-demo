package com.webflux.customerportfolio.dto;

import com.webflux.customerportfolio.domain.Ticker;
import com.webflux.customerportfolio.domain.TradeAction;

public record StockTradeResponse(int customerId,
                                 Ticker ticker,
                                 double price,
                                 int quantity,
                                 TradeAction action,
                                 double totalPrice,
                                 double balance) {
}
