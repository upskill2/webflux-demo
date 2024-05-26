package com.weblux.demo;

import com.weblux.demo.config.WebClientConfig;
import demo.WebfluxDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {WebfluxDemoApplication.class, WebClientConfig.class})
 class BaseTest {

    @Test
    void contextLoads () {
    }
}
