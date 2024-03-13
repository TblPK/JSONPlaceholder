package com.example.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.util.Arrays;

/**
 * Configuration class for WebClient.
 */
@Configuration
@Log4j2
public class WebClientConfiguration {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    /**
     * Configures RestClient bean with base URL.
     *
     * @param builder The RestClient.Builder object.
     * @return The configured RestClient bean.
     */
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl(BASE_URL)
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                    log.error("Client Error Status " + response.getStatusCode());
                    log.error("Client Error Body " + Arrays.toString(response.getBody().readAllBytes()));
                })
                .build();
    }
}
