package com.webflux_microservices.order_service.util;

import com.webflux_microservices.order_service.entity.PurchaseOrderEntity;
import com.weblux.demo.dto.orderservice.PurchaseOrderRequestDto;
import com.weblux.demo.dto.orderservice.PurchaseOrderResponseDto;
import com.weblux.demo.dto.orderservice.PurchaseOrderStatus;
import com.weblux.demo.dto.orderservice.RequestContext;
import com.weblux.demo.dto.userservice.TransactionRequestDto;
import com.weblux.demo.dto.userservice.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper (componentModel = "spring")
public abstract class ContextMapper {


    @Mapping (target = "userId", source = "requestContext.productDTO.id")
    @Mapping (target = "price", source = "requestContext.productDTO.price")
    public abstract TransactionRequestDto mapToTransactionRequestDto (RequestContext requestContext);


    @Mapping (target = "userId", source = "requestContext.transactionResponseDto.userId")
    @Mapping (target = "productId", source = "requestContext.purchaseOrderRequestDto.productId")
    @Mapping (target = "status", source = "requestContext", qualifiedByName = "setStatus")
    public abstract PurchaseOrderRequestDto mapToPurchaseOrderRequest (RequestContext requestContext);


    @Mapping (target = "productId", source = "requestContext.purchaseOrderRequestDto.productId")
    @Mapping (target = "userId", source = "requestContext.transactionResponseDto.userId")
    @Mapping (target = "amount", source = "requestContext.productDTO.price")
    @Mapping (target = "status", source = "requestContext.purchaseOrderRequestDto.status")
    public abstract PurchaseOrderEntity contextToPurchaseEntity(RequestContext requestContext);

    @Named ("setStatus")
    public PurchaseOrderStatus setStatus (RequestContext requestContext) {
        return requestContext.getTransactionResponseDto ().getStatus () == TransactionResponseDto.TransactionsStatus.COMPLETED
                ? PurchaseOrderStatus.COMPLETED : PurchaseOrderStatus.FAILED;
    }

    @Mapping (target = "orderId", source = "id")
    public abstract PurchaseOrderResponseDto purchaseEntityToPurchaseResponse(PurchaseOrderEntity purchaseOrderEntity);

}
