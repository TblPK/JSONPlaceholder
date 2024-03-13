package com.example.demo.service;

import com.example.demo.entity.PostDto;
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
@CacheConfig(cacheNames = {"postCache"})
public class PostService implements JsonPlaceholderService<PostDto> {
    private final String HTTP_METHOD = "posts";
    private final RestClient restClient;

    @Cacheable()
    public List<PostDto> findAll() {
        log.debug("Finding all posts");
        List<PostDto> posts = restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        log.debug("Found {} posts", posts.size());
        return posts;
    }

    @Cacheable(key = "#id")
    public PostDto findById(String id) {
        log.debug("Finding post by id: {}", id);
        PostDto post = restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(PostDto.class);
        log.debug("Found post: {}", post);
        return post;
    }

    @CachePut(key = "#result.id")
    public PostDto create(PostDto postDto) {
        log.debug("Creating new post: {}", postDto);
        PostDto createdPost = restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(postDto)
                .retrieve()
                .body(PostDto.class);
        log.debug("Created post: {}", createdPost);
        return createdPost;
    }

    @CachePut(key = "#id")
    public PostDto update(String id, PostDto postDto) {
        log.debug("Updating post with id {}: {}", id, postDto);
        PostDto updatedPost = restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(postDto)
                .retrieve()
                .body(PostDto.class);
        log.debug("Updated post: {}", updatedPost);
        return updatedPost;
    }

    @CacheEvict(key = "#id")
    public void delete(String id) {
        log.debug("Deleting post with id: {}", id);
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
        log.debug("Deleted post with id: {}", id);
    }
}
