package com.example.demo.service;

import com.example.demo.entity.UserDto;
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
@CacheConfig(cacheNames = {"userCache"})
public class UserService implements JsonPlaceholderService<UserDto> {
    private final String HTTP_METHOD = "users";
    private final RestClient restClient;

    @Cacheable()
    public List<UserDto> findAll() {
        return restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Cacheable(key = "#id")
    public UserDto findById(String id) {
        return restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(UserDto.class);
    }

    @CachePut(key = "#result.id")
    public UserDto create(UserDto userDto) {
        return restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(userDto)
                .retrieve()
                .body(UserDto.class);
    }

    @CachePut(key = "#id")
    public UserDto update(String id, UserDto userDto) {
        return restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(userDto)
                .retrieve()
                .body(UserDto.class);
    }

    @CacheEvict(key = "#id")
    public void delete(String id) {
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }
}
