package com.webflux.customerportfolio.entity;

import com.weblux.demo.commons.dto.domain.Ticker;
import org.springframework.data.annotation.Id;

public class PortfolioItem {

    @Id
    private int id;
    private int customerId;
    private Ticker ticker;
    private int quantity;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getCustomerId () {
        return customerId;
    }

    public void setCustomerId (int customerId) {
        this.customerId = customerId;
    }

    public Ticker getTicker () {
        return ticker;
    }

    public void setTicker (Ticker ticker) {
        this.ticker = ticker;
    }

    public int getQuantity () {
        return quantity;
    }

    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }

    public PortfolioItem (int customerId, Ticker ticker, int quantity) {
        this.customerId = customerId;
        this.ticker = ticker;
        this.quantity = quantity;
    }
}
