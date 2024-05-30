package com.webflux.microservice.product_service.util;

import com.webflux.microservice.product_service.dto.ProductDTO;
import com.webflux.microservice.product_service.entity.Product;
import org.mapstruct.Mapper;


@Mapper (componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductDTO toProductDto (Product product);

    public abstract  Product toProductEntity (ProductDTO product);

}