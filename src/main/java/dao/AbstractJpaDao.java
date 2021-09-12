package dao;

import jpa.EntityManagerHelper;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

    protected Class<T> clazz;

    protected EntityManager entityManager;

    public AbstractJpaDao(Class<T> clazzToSet) {
        this.entityManager = EntityManagerHelper.getEntityManager();
        this.clazz = clazzToSet;
    }
    
    public Optional<T> findOne(K id) {
        T t = entityManager.find(clazz, id);
        return Optional.of(t);
    }

    public List<T> findAll() {
        return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
    }

    public T save(T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        entityManager.persist(entity);
        t.commit();

        return entity;
    }

    public T update(final T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        T res = entityManager.merge(entity);
        t.commit();
        return res;

    }

    public void delete(T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        entityManager.remove(entity);
        t.commit();

    }

    public void deleteById(K entityId) {
        Optional<T> entity = findOne(entityId);
        delete(entity.get());
    }

    public boolean existsById(K entityId) {
       return findOne(entityId).isPresent();
    }
}