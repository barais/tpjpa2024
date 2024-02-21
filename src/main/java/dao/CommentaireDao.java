package dao;

import busi.Commentaire;
import jakarta.persistence.EntityManager;

public class CommentaireDao {
    private final EntityManager entityManager;

    public CommentaireDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Commentaire commentaire) {
        entityManager.getTransaction().begin();
        entityManager.persist(commentaire);
        entityManager.getTransaction().commit();
    }

    public void update(Commentaire commentaire) {
        entityManager.getTransaction().begin();
        entityManager.merge(commentaire);
        entityManager.getTransaction().commit();
    }

    public void delete(Long commentaireId) {
        Commentaire commentaire = getById(commentaireId);
        if (commentaire != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(commentaire);
            entityManager.getTransaction().commit();
        }
    }

    public Commentaire getById(Long commentaireId) {
        return entityManager.find(Commentaire.class, commentaireId);
    }
}
