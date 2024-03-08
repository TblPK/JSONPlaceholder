package com.example.demo.model;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}