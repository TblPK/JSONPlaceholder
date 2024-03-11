package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WebClientConfiguration {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl(BASE_URL).build();
    }
}