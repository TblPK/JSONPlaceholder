package com.example.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}