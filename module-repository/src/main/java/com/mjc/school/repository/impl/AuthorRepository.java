package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    @Override
    public List<AuthorModel> readAll() {
        return List.of();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        return null;
    }

    @Override
    public Optional<AuthorModel> update(AuthorModel entity, Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }
}
