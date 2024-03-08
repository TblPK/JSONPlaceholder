package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService implements JsonPlaceholderService<Album> {
    private final String HTTP_METHOD = "albums";
    private final WebClient webClient;

    public Mono<List<Album>> findAll() {
        return webClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<Album> findById(String id) {
        return webClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .bodyToMono(Album.class);
    }

    public Mono<Album> create(Album album) {
        return webClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .bodyValue(album)
                .retrieve()
                .bodyToMono(Album.class);
    }

    public Mono<Album> update(String id, Album album) {
        return webClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .bodyValue(album)
                .retrieve()
                .bodyToMono(Album.class);
    }

    public void delete(String id) {
        webClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }
}
