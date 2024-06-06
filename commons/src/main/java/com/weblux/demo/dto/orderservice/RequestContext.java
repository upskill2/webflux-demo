package com.weblux.demo.dto.orderservice;

import com.weblux.demo.dto.productservice.ProductDTO;
import com.weblux.demo.dto.userservice.TransactionRequestDto;
import com.weblux.demo.dto.userservice.TransactionResponseDto;

public class RequestContext {

    private PurchaseOrderRequestDto purchaseOrderRequestDto;
    private ProductDTO productDTO;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;

    public RequestContext (PurchaseOrderRequestDto purchaseOrderRequestDto) {
        this.purchaseOrderRequestDto = purchaseOrderRequestDto;
    }

    public PurchaseOrderRequestDto getPurchaseOrderRequestDto () {
        return purchaseOrderRequestDto;
    }

    public void setPurchaseOrderRequestDto (PurchaseOrderRequestDto purchaseOrderRequestDto) {
        this.purchaseOrderRequestDto = purchaseOrderRequestDto;
    }

    public ProductDTO getProductDTO () {
        return productDTO;
    }

    public void setProductDTO (ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public TransactionRequestDto getTransactionRequestDto () {
        return transactionRequestDto;
    }

    public void setTransactionRequestDto (TransactionRequestDto transactionRequestDto) {
        this.transactionRequestDto = transactionRequestDto;
    }

    public TransactionResponseDto getTransactionResponseDto () {
        return transactionResponseDto;
    }

    public void setTransactionResponseDto (TransactionResponseDto transactionResponseDto) {
        this.transactionResponseDto = transactionResponseDto;
    }

}

