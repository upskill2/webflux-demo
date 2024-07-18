package com.weblux.demo.commons.dto.customer;

import com.weblux.demo.commons.dto.domain.Ticker;
import com.weblux.demo.commons.dto.domain.TradeAction;

public record StockTradeResponse(int customerId,
                                 Ticker ticker,
                                 double price,
                                 int quantity,
                                 TradeAction action,
                                 double totalPrice,
                                 double balance) {
}
