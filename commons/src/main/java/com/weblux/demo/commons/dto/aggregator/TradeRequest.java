package com.weblux.demo.commons.dto.aggregator;

import com.weblux.demo.commons.dto.domain.Ticker;
import com.weblux.demo.commons.dto.domain.TradeAction;

public record TradeRequest(Ticker ticker, TradeAction action, Integer quantity) {
}
