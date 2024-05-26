package com.webflux.microservice.product_service.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.MongoClientSettings;
import com.webflux.microservice.product_service.entity.Product;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.beans.PropertyEditor;
import java.util.UUID;

@Configuration
public class MongoConfig {

    @Bean
    public UuidIdentifiedEntityEventListener uuidIdentifiedEntityEventListener () {
        return new UuidIdentifiedEntityEventListener ();
    }

/*    @Bean
    public ObjectMapper objectMapper () {
        ObjectMapper mapper = new ObjectMapper ();
        mapper.configure (SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }*/
}
