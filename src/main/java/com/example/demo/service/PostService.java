package com.example.demo.service;

import com.example.demo.entity.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PostService implements JsonPlaceholderService<PostDto> {
    private final String HTTP_METHOD = "posts";
    private final RestClient restClient;
    private final Map<String, PostDto> cache = new ConcurrentHashMap<>();

    public List<PostDto> findAll() {
        return restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public PostDto findById(String id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }

        return restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(PostDto.class);
    }

    public PostDto create(PostDto postDto) {
        return restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(postDto)
                .retrieve()
                .body(PostDto.class);
    }

    public PostDto update(String id, PostDto postDto) {
        return restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(postDto)
                .retrieve()
                .body(PostDto.class);
    }

    public void delete(String id) {
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .toBodilessEntity();
    }

}