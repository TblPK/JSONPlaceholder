package com.example.demo.controller;

import com.example.demo.entity.PostDto;
import com.example.demo.service.PostJService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling operations related to posts.
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostJService PostJService;

    /**
     * Retrieves all posts.
     *
     * @return List of PostDto entities.
     */
    @GetMapping
    List<PostDto> findAll() {
        return PostJService.findAll();
    }

    /**
     * Retrieves a post by its ID.
     * @param id The ID of the post to retrieve.
     * @return The PostDto entity.
     */
    @GetMapping("/{id}")
    PostDto findById(@PathVariable String id) {
        return PostJService.findById(id);
    }

    /**
     * Creates a new post.
     * @param postDto The PostDto entity to create.
     * @return The created PostDto entity.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PostDto create(@RequestBody PostDto postDto) {
        return PostJService.create(postDto);
    }

    /**
     * Updates an existing post.
     * @param id      The ID of the post to update.
     * @param postDto The updated PostDto entity.
     * @return The updated PostDto entity.
     */
    @PutMapping("/{id}")
    PostDto update(@PathVariable String id, @RequestBody PostDto postDto) {
        return PostJService.update(id, postDto);
    }

    /**
     * Deletes a post by its ID.
     * @param id The ID of the post to delete.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        PostJService.delete(id);
    }
}
