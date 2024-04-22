package com.mjc.school.repository;

import com.mjc.school.repository.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseEntity<K>, K> {

    List<T> readAll();

    Optional<T> readById(K id);

    T create(T entity);

    Optional<T> update(T entity, K id);

    boolean deleteById(K id);

    boolean existById(K id);
}
