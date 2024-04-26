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

import java.time.LocalDateTime;
import java.util.*;

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
            Set<TagModel> tagToAdd = new HashSet<>();
            for (TagModel tagModel : entity.getTags()) {
                TagModel existingTag = entityManager.find(TagModel.class, tagModel.getId());
                tagToAdd.add(existingTag);
            }
            entity.setTags(tagToAdd);

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
        NewsModel news = entityManager.find(NewsModel.class, id);
        if (news != null) {
            entityManager.remove(news);
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

    public List<NewsModel> getNewsByTagName(String name) {
        return entityManager.createQuery("select n from NewsModel n join n.tags t where t.name = :name", NewsModel.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<NewsModel> getNewsByTagIds(Long id) {
        return entityManager.createQuery("select n from NewsModel n join n.tags t where t.id = :id", NewsModel.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<NewsModel> getNewsByAuthorName(String name) {
        return entityManager.createQuery("select n from NewsModel n join n.author a where a.name = :name", NewsModel.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<NewsModel> getNewsByTitle(String title) {
        return entityManager.createQuery("select n from NewsModel n where n.title = :title", NewsModel.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<NewsModel> getNewsByContent(String content) {
        return entityManager.createQuery("select n from NewsModel n where n.content = :content", NewsModel.class)
                .setParameter("content", content)
                .getResultList();
    }
}
