package com.example.demo.service;

import java.util.List;

public interface JsonPlaceholderService<T> {

    List<T> findAll();

    T findById(String id);

    T create(T t);

    T update(String id, T t);

    void delete(String id);

}