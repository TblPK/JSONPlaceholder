package com.example.demo.service;

import com.example.demo.entity.UserDto;
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
@CacheConfig(cacheNames = {"userCache"})
public class UserService implements JsonPlaceholderService<UserDto> {
    private final String HTTP_METHOD = "users";
    private final RestClient restClient;

    @Cacheable()
    public List<UserDto> findAll() {
        log.debug("Finding all users");
        List<UserDto> users = restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        log.debug("Found {} users", users.size());
        return users;
    }

    @Cacheable(key = "#id")
    public UserDto findById(String id) {
        log.debug("Finding user by id: {}", id);
        UserDto user = restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(UserDto.class);
        log.debug("Found user: {}", user);
        return user;
    }

    @CachePut(key = "#result.id")
    public UserDto create(UserDto userDto) {
        log.debug("Creating new user: {}", userDto);
        UserDto createdUser = restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(userDto)
                .retrieve()
                .body(UserDto.class);
        log.debug("Created user: {}", createdUser);
        return createdUser;
    }

    @CachePut(key = "#id")
    public UserDto update(String id, UserDto userDto) {
        log.debug("Updating user with id {}: {}", id, userDto);
        UserDto updatedUser = restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(userDto)
                .retrieve()
                .body(UserDto.class);
        log.debug("Updated user: {}", updatedUser);
        return updatedUser;
    }

    @CacheEvict(key = "#id")
    public void delete(String id) {
        log.debug("Deleting user with id: {}", id);
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
        log.debug("Deleted user with id: {}", id);
    }
}
