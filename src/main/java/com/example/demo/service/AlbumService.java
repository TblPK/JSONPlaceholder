package com.example.demo.service;

import com.example.demo.entity.AlbumDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"albumCache"})
public class AlbumService implements JsonPlaceholderService<AlbumDto> {
    private final String HTTP_METHOD = "albums";
    private final RestClient restClient;

    @Cacheable()
    public List<AlbumDto> findAll() {
        log.debug("Finding all albums");
        List<AlbumDto> albums = restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        log.debug("Found {} albums", albums.size());
        return albums;
    }

    @Cacheable(key = "#id")
    public AlbumDto findById(String id) {
        log.debug("Finding album by id: {}", id);
        AlbumDto album = restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(AlbumDto.class);
        log.debug("Found album: {}", album);
        return album;
    }

    @CachePut(key = "#result.id")
    public AlbumDto create(AlbumDto albumDto) {
        log.debug("Creating new album: {}", albumDto);
        AlbumDto createdAlbum = restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(albumDto)
                .retrieve()
                .body(AlbumDto.class);
        log.debug("Created album: {}", createdAlbum);
        return createdAlbum;
    }

    @CachePut(key = "#id")
    public AlbumDto update(String id, AlbumDto albumDto) {
        log.debug("Updating album with id {}: {}", id, albumDto);
        AlbumDto updatedAlbum = restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(albumDto)
                .retrieve()
                .body(AlbumDto.class);
        log.debug("Updated album: {}", updatedAlbum);
        return updatedAlbum;
    }

    @CacheEvict(key = "#id")
    public void delete(String id) {
        log.debug("Deleting album with id: {}", id);
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
        log.debug("Deleted album with id: {}", id);
    }
}
