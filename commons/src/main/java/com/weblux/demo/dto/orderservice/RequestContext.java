package com.weblux.demo.dto.orderservice;

import com.weblux.demo.dto.productservice.ProductDTO;
import com.weblux.demo.dto.userservice.TransactionRequestDto;
import com.weblux.demo.dto.userservice.TransactionResponseDto;
import lombok.Data;

@Data
public class RequestContext {

    private PurchaseOrderRequestDto purchaseOrderRequestDto;
    private ProductDTO productDTO;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;

    public RequestContext (PurchaseOrderRequestDto purchaseOrderRequestDto) {
        this.purchaseOrderRequestDto = purchaseOrderRequestDto;
    }
}

