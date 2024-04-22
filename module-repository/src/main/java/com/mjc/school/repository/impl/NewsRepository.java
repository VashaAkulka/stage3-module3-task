package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    @Override
    public List<NewsModel> readAll() {
        return List.of();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        return null;
    }

    @Override
    public Optional<NewsModel> update(NewsModel entity, Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return true;
    }
}
