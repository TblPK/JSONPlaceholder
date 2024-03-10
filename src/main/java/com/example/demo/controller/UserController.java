package com.example.demo.controller;

import com.example.demo.entity.UserDto;
import com.example.demo.service.UserJService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserJService UserJService;

    @GetMapping
    Mono<List<UserDto>> findAll() {
        return UserJService.findAll();
    }

    @GetMapping("/{id}")
    Mono<UserDto> findById(@PathVariable String id) {
        return UserJService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<UserDto> create(@RequestBody UserDto userDto) {
        return UserJService.create(userDto);
    }

    @PutMapping("/{id}")
    Mono<UserDto> update(@PathVariable String id, @RequestBody UserDto userDto) {
        return UserJService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        UserJService.delete(id);
    }

}
