package com.example.demo.controller;

import com.example.demo.entity.AlbumDto;
import com.example.demo.service.AlbumJService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling operations related to albums.
 */
@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumJService AlbumJService;

    /**
     * Retrieves all albums.
     *
     * @return List of AlbumDto entities.
     */
    @GetMapping
    List<AlbumDto> findAll() {
        return AlbumJService.findAll();
    }

    /**
     * Retrieves an album by its ID.
     * @param id The ID of the album to retrieve.
     * @return The AlbumDto entity.
     */
    @GetMapping("/{id}")
    AlbumDto findById(@PathVariable String id) {
        return AlbumJService.findById(id);
    }

    /**
     * Creates a new album.
     * @param albumDto The AlbumDto entity to create.
     * @return The created AlbumDto entity.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AlbumDto create(@RequestBody AlbumDto albumDto) {
        return AlbumJService.create(albumDto);
    }

    /**
     * Updates an existing album.
     * @param id       The ID of the album to update.
     * @param albumDto The updated AlbumDto entity.
     * @return The updated AlbumDto entity.
     */
    @PutMapping("/{id}")
    AlbumDto update(@PathVariable String id, @RequestBody AlbumDto albumDto) {
        return AlbumJService.update(id, albumDto);
    }

    /**
     * Deletes an album by its ID.
     * @param id The ID of the album to delete.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        AlbumJService.delete(id);
    }
}
