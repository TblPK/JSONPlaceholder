package com.example.demo.entity;

/**
 * DTO for album, used with for API interaction.
 */
public record AlbumDto(
        Integer userId,
        Integer id,
        String title
) {
}
