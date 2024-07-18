package com.weblux.demo.commons.dto.customer;

import com.weblux.demo.commons.dto.domain.Ticker;
import com.weblux.demo.commons.dto.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                double price,
                                int quantity,
                                TradeAction action) {

    public double calculateTotalPrice () {
        return price * quantity;
    }

}
