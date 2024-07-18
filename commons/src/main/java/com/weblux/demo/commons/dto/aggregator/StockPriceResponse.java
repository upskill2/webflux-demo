package com.weblux.demo.commons.dto.aggregator;

import com.weblux.demo.commons.dto.domain.Ticker;

public record StockPriceResponse(Ticker ticker, double price) {
}
