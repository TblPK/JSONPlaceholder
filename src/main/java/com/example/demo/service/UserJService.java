package com.example.demo.service;

import com.example.demo.entity.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserJService implements JsonPlaceholderService<UserDto> {
    private final String HTTP_METHOD = "users";
    private final WebClient webClient;

    public Mono<List<UserDto>> findAll() {
        return webClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<UserDto> findById(String id) {
        return webClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Mono<UserDto> create(UserDto UserDto) {
        return webClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .bodyValue(UserDto)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Mono<UserDto> update(String id, UserDto UserDto) {
        return webClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .bodyValue(UserDto)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public void delete(String id) {
        webClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }

}
