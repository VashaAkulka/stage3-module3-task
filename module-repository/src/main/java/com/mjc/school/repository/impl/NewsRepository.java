package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsModel> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> cq = cb.createQuery(NewsModel.class);
        Root<NewsModel> root = cq.from(NewsModel.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        NewsModel tag = entityManager.find(NewsModel.class, id);
        return Optional.ofNullable(tag);
    }

    @Override
    @Transactional
    public NewsModel create(NewsModel entity) {
        if (entity != null) {
            entity.setCreateDate(LocalDateTime.now());
            entity.setLastUpdateDate(LocalDateTime.now());
            entityManager.persist(entity);
        }
        return entity;
    }

    @Override
    @Transactional
    public Optional<NewsModel> update(NewsModel entity, Long id) {
        int updatedEntities = entityManager.createQuery("update NewsModel set title = :title, content = :content, author = :author, lastUpdateDate = :lastUpdateDate, tags = :tags where id = :id")
                .setParameter("title", entity.getTitle())
                .setParameter("content", entity.getContent())
                .setParameter("author", entity.getAuthor())
                .setParameter("lastUpdateDate", LocalDateTime.now())
                .setParameter("tags", entity.getTags())
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
        NewsModel tag = entityManager.find(NewsModel.class, id);
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
        Root<NewsModel> root = cq.from(NewsModel.class);
        cq.select(cb.count(root)).where(cb.equal(root.get("id"), id));
        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }

    public Set<NewsModel> getNewsByTagNames(String name) {
        List<NewsModel> tagList = entityManager.createQuery("select n from NewsModel n join n.tags t where t.name = :name", NewsModel.class)
                .setParameter("name", name)
                .getResultList();
        return new HashSet<>(tagList);
    }

    public Set<NewsModel> getNewsByTagIds(Long id) {
        List<NewsModel> tagList = entityManager.createQuery("select n from NewsModel n join n.tags t where t.id = :id", NewsModel.class)
                .setParameter("id", id)
                .getResultList();
        return new HashSet<>(tagList);
    }

    public Set<NewsModel> getNewsByAuthorName(String name) {
        List<NewsModel> tagList = entityManager.createQuery("select n from NewsModel n join n.author a where a.name = :name", NewsModel.class)
                .setParameter("name", name)
                .getResultList();
        return new HashSet<>(tagList);
    }

    public Set<NewsModel> getNewsByTitle(String title) {
        List<NewsModel> tagList = entityManager.createQuery("select n from NewsModel n where n.title = :title", NewsModel.class)
                .setParameter("title", title)
                .getResultList();
        return new HashSet<>(tagList);
    }

    public Set<NewsModel> getNewsByContent(String content) {
        List<NewsModel> tagList = entityManager.createQuery("select n from NewsModel n where n.content = :content", NewsModel.class)
                .setParameter("content", content)
                .getResultList();
        return new HashSet<>(tagList);
    }
}
