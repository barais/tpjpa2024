package service;

import busi.Role;
import jakarta.persistence.EntityManager;

public class RoleService {
    private EntityManager manager;

    public RoleService(EntityManager manager) {
        this.manager = manager;
    }

    // Créer un nouveau rôle
    public void create(Role role) {
        manager.getTransaction().begin();
        manager.persist(role);
        manager.getTransaction().commit();
    }

    // Mettre à jour un rôle existant
    public void update(Role role) {
        manager.getTransaction().begin();
        manager.merge(role);
        manager.getTransaction().commit();
    }

    // Supprimer un rôle existant
    public void delete(Long roleId) {
        manager.getTransaction().begin();
        Role role = manager.find(Role.class, roleId);
        manager.remove(role);
        manager.getTransaction().commit();
    }

    // Récupérer un rôle par son identifiant
    public Role getById(Long roleId) {
        return manager.find(Role.class, roleId);
    }
}

