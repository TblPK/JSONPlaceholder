package com.example.demo.service;

import com.example.demo.entity.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"postCache"})
public class PostService implements JsonPlaceholderService<PostDto> {
    private final String HTTP_METHOD = "posts";
    private final RestClient restClient;

    @Cacheable()
    public List<PostDto> findAll() {
        return restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Cacheable(key = "#id")
    public PostDto findById(String id) {
        return restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(PostDto.class);
    }

    @CachePut(key = "#result.id")
    public PostDto create(PostDto postDto) {
        return restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(postDto)
                .retrieve()
                .body(PostDto.class);
    }

    @CachePut(key = "#id")
    public PostDto update(String id, PostDto postDto) {
        return restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(postDto)
                .retrieve()
                .body(PostDto.class);
    }

    @CacheEvict(key = "#id")
    public void delete(String id) {
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }
}
