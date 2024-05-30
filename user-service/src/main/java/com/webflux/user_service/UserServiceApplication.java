package com.webflux.user_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;


@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger (UserServiceApplication.class);

    @Value ("classpath:sqls/init.sql")
    private Resource initSql;

    @Autowired
    private R2dbcEntityTemplate template;

    public static void main (String[] args) {
        SpringApplication.run (UserServiceApplication.class, args);
    }

    @Override
    public void run (final String... args) throws Exception {
        final String query = StreamUtils.copyToString (initSql.getInputStream (), StandardCharsets.UTF_8);
        logger.info (query);

        template
                .getDatabaseClient ()
                .sql (query)
                .then ()
                .subscribe ();
    }

}
