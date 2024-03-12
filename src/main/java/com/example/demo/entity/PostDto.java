package com.example.demo.entity;

/**
 * DTO for post, used with for API interaction.
 */
public record PostDto(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}