package com.weblux.demo.commons.dto.aggregator;

import com.weblux.demo.commons.dto.domain.Ticker;

import java.time.LocalDateTime;

public record PriceUpdate(Ticker ticker, Integer price, LocalDateTime time) {
}
