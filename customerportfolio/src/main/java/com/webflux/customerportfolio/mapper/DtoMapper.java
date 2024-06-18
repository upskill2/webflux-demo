package com.webflux.customerportfolio.mapper;

import com.webflux.customerportfolio.dto.CustomerInformation;
import com.webflux.customerportfolio.dto.StockTradeRequest;
import com.webflux.customerportfolio.dto.StockTradeResponse;
import com.webflux.customerportfolio.entity.Customer;
import com.webflux.customerportfolio.entity.PortfolioItem;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper (componentModel = "spring")
public abstract class DtoMapper {

    @Mapping (target = "holdings", source = "items")
    public abstract CustomerInformation mapToCustomerInformation (Customer customer, List<PortfolioItem> items);

    public abstract PortfolioItem toPortfolioItemFromRequest (StockTradeRequest stockTradeRequest);

    @Mapping (target = "totalPrice", source = "stockTradeRequest", qualifiedByName = "calculateTotalPrice")
    public abstract StockTradeResponse toStockTradeResponse (StockTradeRequest stockTradeRequest, int customerId, int balance);

    @Named ("calculateTotalPrice")
    public double calculateTotalPrice (StockTradeRequest stockTradeRequest) {
        return stockTradeRequest.calculateTotalPrice ();
    }

}
