package com.example.demo.entity;

public record PostDto(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}