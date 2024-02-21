package dao;

import busi.Fiche;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * @autor nana
 */
public class FicheDao {
    private final EntityManager entityManager;

    public FicheDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Fiche fiche) {
        entityManager.getTransaction().begin();
        entityManager.persist(fiche);
        entityManager.getTransaction().commit();
    }

    public void update(Fiche fiche) {
        entityManager.getTransaction().begin();
        entityManager.merge(fiche);
        entityManager.getTransaction().commit();
    }

    public void delete(Long ficheId) {
        Fiche fiche = getById(ficheId);
        if (fiche != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(fiche);
            entityManager.getTransaction().commit();
        }
    }

    public Fiche getById(Long ficheId) {
        return entityManager.find(Fiche.class, ficheId);
    }

    // Récupérer toutes les fiches
    public List<Fiche> getAllFiches() {
        return entityManager.createQuery("SELECT f FROM Fiche f", Fiche.class).getResultList();
    }
}
