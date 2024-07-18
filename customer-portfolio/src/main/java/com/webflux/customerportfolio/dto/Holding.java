package com.webflux.customerportfolio.dto;

import com.webflux.customerportfolio.domain.Ticker;

public record Holding(Ticker ticker, Integer quantity) {
}
