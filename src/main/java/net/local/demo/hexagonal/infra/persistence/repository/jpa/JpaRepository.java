package net.local.demo.hexagonal.infra.persistence.repository.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

public abstract class JpaRepository<T, ID extends Serializable> {
    
    private Class<T> persistedClass;
    private final EntityManager entityManager;
 
    protected JpaRepository(Class<T> persistedClass, final EntityManager entityManager) {
        this.persistedClass = persistedClass;
        this.entityManager = entityManager;
    }

    public List<T> loadAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistedClass);
        query.from(persistedClass);
        return entityManager.createQuery(query).getResultList();
    }

    public Optional<T> loadById(ID entityID) {
        return Optional.of(entityManager.find(persistedClass, entityID));
    }

    public T persist(@Valid T entity) {
        return entityManager.merge(entity);
    }
}
