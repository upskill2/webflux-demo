package com.webflux.customerportfolio.dto;

import com.webflux.customerportfolio.domain.Ticker;
import com.webflux.customerportfolio.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                double price,
                                int quantity,
                                TradeAction action) {

    public double calculateTotalPrice () {
        return price * quantity;
    }

}
