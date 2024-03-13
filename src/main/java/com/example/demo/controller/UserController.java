package com.example.demo.controller;

import com.example.demo.entity.UserDto;
import com.example.demo.service.JsonPlaceholderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final JsonPlaceholderService<UserDto> UserService;

    @GetMapping("/")
    List<UserDto> findAll() {
        return UserService.findAll();
    }

    @GetMapping("/{id}")
    UserDto findById(@PathVariable String id) {
        return UserService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    UserDto create(@RequestBody UserDto userDto) {
        return UserService.create(userDto);
    }

    @PutMapping("/{id}")
    UserDto update(@PathVariable String id, @RequestBody UserDto userDto) {
        return UserService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        UserService.delete(id);
    }
}
