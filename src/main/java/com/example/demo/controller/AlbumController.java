package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.service.JsonPlaceholderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final JsonPlaceholderService<Album> albumService;

    @GetMapping
    Mono<List<Album>> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    Mono<Album> findById(@PathVariable String id) {
        return albumService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Album> create(@RequestBody Album album) {
        return albumService.create(album);
    }

    @PutMapping("/{id}")
    Mono<Album> update(@PathVariable String id, @RequestBody Album album) {
        return albumService.update(id, album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        albumService.delete(id);
    }
}
