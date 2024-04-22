package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<AuthorModel> readAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorModel> cq = cb.createQuery(AuthorModel.class);
        Root<AuthorModel> root = cq.from(AuthorModel.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        AuthorModel tag = entityManager.find(AuthorModel.class, id);
        return Optional.ofNullable(tag);
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        if (entity != null) {
            entity.setCreateDate(LocalDateTime.now());
            entity.setLastUpdateDate(LocalDateTime.now());
            entityManager.persist(entity);
        }
        return entity;
    }

    @Override
    public Optional<AuthorModel> update(AuthorModel entity, Long id) {
        int updatedEntities = entityManager.createQuery("update AuthorModel set name = :name, lastUpdateDate = :lastUpdateDate where id = :id")
                .setParameter("name", entity.getName())
                .setParameter("lastUpdateDate", LocalDateTime.now())
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
        AuthorModel tag = entityManager.find(AuthorModel.class, id);
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
        Root<AuthorModel> root = cq.from(AuthorModel.class);
        cq.select(cb.count(root)).where(cb.equal(root.get("id"), id));
        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }

    private AuthorModel getAuthorByNewsId(Long newsId) {
        return entityManager.createQuery("select a from AuthorModel a join a.news n where n.id = :id", AuthorModel.class)
                .setParameter("id", newsId)
                .getSingleResult();
    }
}
