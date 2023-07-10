package com.bmc.sfg.beer.order.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

/**
 * @author Masoumeh Yeganeh
 * @created 10/07/2023
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public BasicAuthenticationInterceptor basicAuthenticationInterceptor(
            @Value("${sfg.brewery.beer-service-user}") String inventoryUser,
            @Value("${sfg.brewery.beer-service-password}") String inventoryPassword){
        return new BasicAuthenticationInterceptor(inventoryUser,inventoryPassword);
    }
}