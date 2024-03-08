package com.example.demo.service;

import reactor.core.publisher.Mono;

import java.util.List;

public interface JsonPlaceholderService<T> {

    Mono<List<T>> findAll();

    Mono<T> findById(String id);

    Mono<T> create(T t);

    Mono<T> update(String id, T t);

    void delete(String id);

}