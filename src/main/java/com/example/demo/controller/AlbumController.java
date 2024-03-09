package com.example.demo.controller;

import com.example.demo.entity.AlbumDto;
import com.example.demo.service.JSAlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final JSAlbumService JSAlbumService;

    @GetMapping
    Mono<List<AlbumDto>> findAll() {
        return JSAlbumService.findAll();
    }

    @GetMapping("/{id}")
    Mono<AlbumDto> findById(@PathVariable String id) {
        return JSAlbumService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<AlbumDto> create(@RequestBody AlbumDto albumDto) {
        return JSAlbumService.create(albumDto);
    }

    @PutMapping("/{id}")
    Mono<AlbumDto> update(@PathVariable String id, @RequestBody AlbumDto albumDto) {
        return JSAlbumService.update(id, albumDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        JSAlbumService.delete(id);
    }
}
