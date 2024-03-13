package com.example.demo.service;

import com.example.demo.entity.AlbumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService implements JsonPlaceholderService<AlbumDto> {
    private final String HTTP_METHOD = "albums";
    private final RestClient restClient;

    public List<AlbumDto> findAll() {
        return restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public AlbumDto findById(String id) {
        return restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(AlbumDto.class);
    }

    public AlbumDto create(AlbumDto albumDto) {
        return restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(albumDto)
                .retrieve()
                .body(AlbumDto.class);
    }

    public AlbumDto update(String id, AlbumDto albumDto) {
        return restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(albumDto)
                .retrieve()
                .body(AlbumDto.class);
    }

    public void delete(String id) {
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }
}