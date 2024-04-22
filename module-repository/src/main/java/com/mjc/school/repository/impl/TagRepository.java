package com.mjc.school.repository.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.TagModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TagModel> cq = cb.createQuery(TagModel.class);
        Root<TagModel> tags = cq.from(TagModel.class);
        cq.select(tags);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TagModel> cq = cb.createQuery(TagModel.class);
        Root<TagModel> tags = cq.from(TagModel.class);
        cq.select(tags).where(cb.equal(tags.get("id"), id));
        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public TagModel create(TagModel entity) {
        return null;
    }

    @Override
    public Optional<TagModel> update(TagModel entity, Long id) {
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
