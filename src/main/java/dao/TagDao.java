package dao;

import busi.Tag;
import jakarta.persistence.EntityManager;

public class TagDao {
    private EntityManager manager;

    public TagDao(EntityManager manager) {
        this.manager = manager;
    }

    // Créer un nouveau tag
    public void create(Tag tag) {
        manager.getTransaction().begin();
        manager.persist(tag);
        manager.getTransaction().commit();
    }

    // Mettre à jour un tag existant
    public void update(Tag tag) {
        manager.getTransaction().begin();
        manager.merge(tag);
        manager.getTransaction().commit();
    }

    // Supprimer un tag existant
    public void delete(Long tagId) {
        manager.getTransaction().begin();
        Tag tag = manager.find(Tag.class, tagId);
        manager.remove(tag);
        manager.getTransaction().commit();
    }

    // Récupérer un tag par son identifiant
    public Tag getById(Long tagId) {
        return manager.find(Tag.class, tagId);
    }
}
