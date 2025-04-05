package org.example.hotelbookingsystem.application.contracts;

import java.util.Optional;

public interface IGenericRepository<T, TID> {
    // Get all entities
    Iterable<T> getAllEntities();

    // Get an entity by its ID
    Optional<T> getEntityById(TID id);

    // Create an entity
    T createEntity(T entity);

    // Update an entity
    T updateEntity(T entity);

    // Delete an entity by its ID
    void deleteEntity(TID id);

    // Save changes to the database
    void save();
}
