package com.mjc.school.repository.impl;


import com.mjc.school.repository.BaseRepository;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
    @Transactional
    public TagModel create(TagModel entity) {
        if (entity != null) {
            Set<NewsModel> newsToAdd = new HashSet<>();
            for (NewsModel newsModel : entity.getNews()) {
                NewsModel existingNews = entityManager.find(NewsModel.class, newsModel.getId());
                newsToAdd.add(existingNews);
            }
            entity.setNews(newsToAdd);
            entityManager.persist(entity);
        }
        return entity;
    }

    @Override
    @Transactional
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
    @Transactional
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

    public List<TagModel> getTagsByNewsId(Long newsId) {
        return entityManager.createQuery("select t from TagModel t join t.news n where n.id = :id", TagModel.class)
                .setParameter("id", newsId)
                .getResultList();
    }
}
