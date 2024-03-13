package com.example.demo.controller;

import com.example.demo.entity.AlbumDto;
import com.example.demo.service.JsonPlaceholderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final JsonPlaceholderService<AlbumDto> AlbumService;

    @GetMapping("/")
    List<AlbumDto> findAll() {
        return AlbumService.findAll();
    }

    @GetMapping("/{id}")
    AlbumDto findById(@PathVariable String id) {
        return AlbumService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    AlbumDto create(@RequestBody AlbumDto albumDto) {
        return AlbumService.create(albumDto);
    }

    @PutMapping("/{id}")
    AlbumDto update(@PathVariable String id, @RequestBody AlbumDto albumDto) {
        return AlbumService.update(id, albumDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        AlbumService.delete(id);
    }
}
