package com.example.demo.service;

import com.example.demo.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements JsonPlaceholderService<Post> {
    private final String HTTP_METHOD = "posts";
    private final WebClient webClient;

    public Mono<List<Post>> findAll() {
        return webClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<Post> findById(String id) {
        return webClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public Mono<Post> findByComments(String id) {
        return webClient
                .get()
                .uri("/{method}/{id}/comments", HTTP_METHOD, id)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public Mono<Post> create(Post post) {
        return webClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public Mono<Post> update(String id, Post post) {
        return webClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class);
    }

    public void delete(String id) {
        webClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }

}