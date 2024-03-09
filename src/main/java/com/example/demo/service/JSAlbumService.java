package com.example.demo.service;

import com.example.demo.entity.AlbumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JSAlbumService implements JsonPlaceholderService<AlbumDto> {
    private final String HTTP_METHOD = "albums";
    private final WebClient webClient;

    public Mono<List<AlbumDto>> findAll() {
        return webClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<AlbumDto> findById(String id) {
        return webClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .bodyToMono(AlbumDto.class);
    }

    public Mono<AlbumDto> create(AlbumDto albumDto) {
        return webClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .bodyValue(albumDto)
                .retrieve()
                .bodyToMono(AlbumDto.class);
    }

    public Mono<AlbumDto> update(String id, AlbumDto albumDto) {
        return webClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .bodyValue(albumDto)
                .retrieve()
                .bodyToMono(AlbumDto.class);
    }

    public void delete(String id) {
        webClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }
}
