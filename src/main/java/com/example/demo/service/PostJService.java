package com.example.demo.service;

import com.example.demo.entity.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostJService implements JsonPlaceholderService<PostDto> {
    private final String HTTP_METHOD = "posts";
    private final WebClient webClient;

    public Mono<List<PostDto>> findAll() {
        return webClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<PostDto> findById(String id) {
        return webClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .bodyToMono(PostDto.class);
    }

    public Mono<PostDto> create(PostDto postDto) {
        return webClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .bodyValue(postDto)
                .retrieve()
                .bodyToMono(PostDto.class);
    }

    public Mono<PostDto> update(String id, PostDto postDto) {
        return webClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .bodyValue(postDto)
                .retrieve()
                .bodyToMono(PostDto.class);
    }

    public void delete(String id) {
        webClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }

}