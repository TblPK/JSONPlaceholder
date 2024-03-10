package com.example.demo.controller;

import com.example.demo.entity.PostDto;
import com.example.demo.service.PostJService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostJService PostJService;

    @GetMapping
    Mono<List<PostDto>> findAll() {
        return PostJService.findAll();
    }

    @GetMapping("/{id}")
    Mono<PostDto> findById(@PathVariable String id) {
        return PostJService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<PostDto> create(@RequestBody PostDto postDto) {
        return PostJService.create(postDto);
    }

    @PutMapping("/{id}")
    Mono<PostDto> update(@PathVariable String id, @RequestBody PostDto postDto) {
        return PostJService.update(id, postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        PostJService.delete(id);
    }

}