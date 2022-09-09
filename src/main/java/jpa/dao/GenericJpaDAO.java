package jpa.dao;

import jpa.EntityManagerHelper;
import org.hibernate.cfg.NotYetImplementedException;

import javax.persistence.EntityManager;
import java.util.List;

public class GenericJpaDAO<T, TK> {

    protected final EntityManager manager;
    protected final Class<T> objectClass;

    protected GenericJpaDAO(Class<T> objectClass) {
        this.manager = EntityManagerHelper.getEntityManager();
        this.objectClass = objectClass;
    }

    public T getById(TK id) {
        return this.manager.find(this.objectClass, id);
    }

    public List<T> getAll() {
        return this.manager
                .createQuery("select obj from " + this.objectClass.getName() + " obj", objectClass)
                .getResultList();
    }

    public boolean exists(TK id) {
        return this.manager.find(this.objectClass, id) != null;
    }

    public void update(TK id) {
        // TODO
        throw new NotYetImplementedException();
    }

    public void remove(TK id) {
        // TODO
        throw new NotYetImplementedException();
    }
}
