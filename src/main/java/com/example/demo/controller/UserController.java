package com.example.demo.controller;

import com.example.demo.entity.UserDto;
import com.example.demo.service.UserJService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling operations related to users.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserJService UserJService;

    /**
     * Retrieves all users.
     *
     * @return List of UserDto entities.
     */
    @GetMapping
    List<UserDto> findAll() {
        return UserJService.findAll();
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The UserDto entity.
     */
    @GetMapping("/{id}")
    UserDto findById(@PathVariable String id) {
        return UserJService.findById(id);
    }

    /**
     * Creates a new user.
     * @param userDto The UserDto entity to create.
     * @return The created UserDto entity.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto create(@RequestBody UserDto userDto) {
        return UserJService.create(userDto);
    }

    /**
     * Updates an existing user.
     * @param id      The ID of the user to update.
     * @param userDto The updated UserDto entity.
     * @return The updated UserDto entity.
     */
    @PutMapping("/{id}")
    UserDto update(@PathVariable String id, @RequestBody UserDto userDto) {
        return UserJService.update(id, userDto);
    }

    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        UserJService.delete(id);
    }
}
