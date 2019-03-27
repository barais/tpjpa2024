package fr.istic.sir.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    /**
     * Save a resource.
     *
     * @param o Resource to save
     * @return Return saved resource
     */
    T save(T o);

    /**
     * Return all resources.
     *
     * @return Return all resources from database.
     */
    List<T> findAll();

    /**
     * Find a single resource.
     *
     * @param column Column that search on.
     * @param value  Research value.
     * @return Return a list of resource.
     */
    List<T> findBy(String column, String value);

    /**
     * Find a resource by it identity.
     *
     * @param id Resource identity.
     * @return Return a resource if the given identity exist, null if not.
     */
    Optional<T> findById(Long id);

    /**
     * Find a resource base on a column
     * @param column Column that search on.
     * @param value Research value.
     * @return Return a resource
     */
    Optional<T> findOne(String column, String value);

    /**
     * Update a resource.
     * @param o Resource to update
     * @return Return updated resource.
     */
    T update(T o);

    /**
     * Delete a resource
     * @param o Resource to delete.
     * @return Return deleted resource
     */
    T delete(T o);
}
