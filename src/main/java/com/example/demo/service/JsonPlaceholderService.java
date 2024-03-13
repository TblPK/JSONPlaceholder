package com.example.demo.service;

import java.util.List;

/**
 * This interface represents a service for interacting with a JSON Placeholder resource.
 *
 * @param <T> the type of objects handled by this service
 */
public interface JsonPlaceholderService<T> {

    /**
     * Retrieves all objects of type T from the JSON Placeholder resource.
     *
     * @return a list containing all objects of type T
     */
    List<T> findAll();

    /**
     * Retrieves an object of type T by its unique identifier from the JSON Placeholder resource.
     *
     * @param id the unique identifier of the object to retrieve
     * @return the object of type T with the given identifier, or null if not found
     */
    T findById(String id);

    /**
     * Creates a new object of type T in the JSON Placeholder resource.
     *
     * @param t the object of type T to create
     * @return the created object of type T
     */
    T create(T t);

    /**
     * Updates an existing object of type T in the JSON Placeholder resource.
     *
     * @param id the unique identifier of the object to update
     * @param t  the updated object of type T
     * @return the updated object of type T
     */
    T update(String id, T t);

    /**
     * Deletes an object of type T from the JSON Placeholder resource.
     *
     * @param id the unique identifier of the object to delete
     */
    void delete(String id);

}
