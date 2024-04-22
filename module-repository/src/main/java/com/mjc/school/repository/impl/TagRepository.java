package com.mjc.school.repository.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.TagModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TagModel> cq = cb.createQuery(TagModel.class);
        Root<TagModel> root = cq.from(TagModel.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        TagModel tag = entityManager.find(TagModel.class, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public TagModel create(TagModel entity) {
        if (entity != null) {
            entityManager.persist(entity);
        }
        return entity;
    }

    @Override
    public Optional<TagModel> update(TagModel entity, Long id) {
        int updatedEntities = entityManager.createQuery("update TagModel set name = :name where id = :id")
                .setParameter("name", entity.getName())
                .setParameter("id", id)
                .executeUpdate();

        if (updatedEntities > 0) {
            return Optional.of(entity);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        TagModel tag = entityManager.find(TagModel.class, id);
        if (tag != null) {
            entityManager.remove(tag);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TagModel> root = cq.from(TagModel.class);
        cq.select(cb.count(root)).where(cb.equal(root.get("id"), id));
        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }

    public Set<TagModel> getTagsByNewsId(Long newsId) {
        List<TagModel> tagList = entityManager.createQuery("select t from TagModel t join t.news n where n.id = :id", TagModel.class)
                .setParameter("id", newsId)
                .getResultList();
        return new HashSet<>(tagList);
    }
}
