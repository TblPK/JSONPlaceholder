package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling administrative operations on users.
 */
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    /**
     * Retrieves all users.
     *
     * @return List of User entities.
     */
    @GetMapping
    List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The User entity.
     */
    @GetMapping("/{id}")
    User findById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    /**
     * Creates a new user.
     * @param user The User entity to create.
     * @return The created User entity.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    User create(@RequestBody User user) {
        return userService.create(user);
    }

    /**
     * Updates an existing user.
     * @param id   The ID of the user to update.
     * @param user The updated User entity.
     * @return The updated User entity.
     */
    @PutMapping("/{id}")
    User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to delete.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
