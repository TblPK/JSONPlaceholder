package com.example.demo.controller;

import com.example.demo.entity.PostDto;
import com.example.demo.service.JsonPlaceholderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final JsonPlaceholderService<PostDto> PostService;

    @GetMapping("/")
    List<PostDto> findAll() {
        return PostService.findAll();
    }

    @GetMapping("/{id}")
    PostDto findById(@PathVariable String id) {
        return PostService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    PostDto create(@RequestBody PostDto postDto) {
        return PostService.create(postDto);
    }

    @PutMapping("/{id}")
    PostDto update(@PathVariable String id, @RequestBody PostDto postDto) {
        return PostService.update(id, postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        PostService.delete(id);
    }
}
