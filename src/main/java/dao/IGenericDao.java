package dao;

import model.Users;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IGenericDao<K, T extends Serializable> {

    Optional<T> findOne(final K id);

    List<T> findAll();

    T save(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final K entityId);

    boolean existsById(final K entityId);
}