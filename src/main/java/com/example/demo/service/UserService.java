package com.example.demo.service;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements JsonPlaceholderService<User> {
    private final String HTTP_METHOD = "users";
    private final WebClient webClient;

    public Mono<List<User>> findAll() {
        return webClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<User> findById(String id) {
        return webClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<User> create(User User) {
        return webClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .bodyValue(User)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<User> update(String id, User User) {
        return webClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .bodyValue(User)
                .retrieve()
                .bodyToMono(User.class);
    }

    public void delete(String id) {
        webClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }

}
