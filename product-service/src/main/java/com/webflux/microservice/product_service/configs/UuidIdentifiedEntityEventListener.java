package com.webflux.microservice.product_service.configs;

import com.webflux.microservice.product_service.entity.Product;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class UuidIdentifiedEntityEventListener extends AbstractMongoEventListener<Product> {

    @Override
    public void onBeforeConvert (final BeforeConvertEvent<Product> event) {
        super.onBeforeConvert (event);
        Product entity = event.getSource();

        if (entity.getId () == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}
