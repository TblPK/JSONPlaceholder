package com.example.demo.service;

import com.example.demo.entity.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements JsonPlaceholderService<UserDto> {
    private final String HTTP_METHOD = "users";
    private final RestClient restClient;

    public List<UserDto> findAll() {
        return restClient
                .get()
                .uri("/{method}/", HTTP_METHOD)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public UserDto findById(String id) {
        return restClient
                .get()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .retrieve()
                .body(UserDto.class);
    }

    public UserDto create(UserDto UserDto) {
        return restClient
                .post()
                .uri("/{method}/", HTTP_METHOD)
                .body(UserDto)
                .retrieve()
                .body(UserDto.class);
    }

    public UserDto update(String id, UserDto UserDto) {
        return restClient
                .put()
                .uri("/{method}/{id}", HTTP_METHOD, id)
                .body(UserDto)
                .retrieve()
                .body(UserDto.class);
    }

    public void delete(String id) {
        restClient
                .delete()
                .uri("/{method}/{id}", HTTP_METHOD, id);
    }

}
