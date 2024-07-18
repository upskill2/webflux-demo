package com.weblux.demo.commons.dto.customer;

import com.weblux.demo.commons.dto.domain.Ticker;

public record Holding(Ticker ticker, Integer quantity) {
}
