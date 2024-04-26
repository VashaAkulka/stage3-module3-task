package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

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
    @Transactional
    public AuthorModel create(AuthorModel entity) {
        if (entity != null) {
            entity.setCreateDate(LocalDateTime.now());
            entity.setLastUpdateDate(LocalDateTime.now());
            entityManager.persist(entity);
        }
        return entity;
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean deleteById(Long id) {
        AuthorModel author = entityManager.find(AuthorModel.class, id);
        if (author != null) {
            entityManager.remove(author);
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

    public AuthorModel getAuthorByNewsId(Long newsId) {
        return entityManager.createQuery("select a from AuthorModel a join a.news n where n.id = :id", AuthorModel.class)
                .setParameter("id", newsId)
                .getSingleResult();
    }
}
