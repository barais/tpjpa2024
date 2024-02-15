package service;

import busi.Utilisateur;
import jakarta.persistence.EntityManager;

public class UtilisateurService {
    private EntityManager manager;

    public UtilisateurService( EntityManager manager) {
        this.manager = manager;
    }

    // Créer un nouvel utilisateur
    public void create(Utilisateur utilisateur) {
        manager.getTransaction().begin();
        manager.persist(utilisateur);
        manager.getTransaction().commit();
    }

    // Mettre à jour un utilisateur existant
    public void update(Utilisateur utilisateur) {
        manager.getTransaction().begin();
        manager.merge(utilisateur);
        manager.getTransaction().commit();
    }

    // Supprimer un utilisateur existant
    public void delete(Long utilisateurId) {
        manager.getTransaction().begin();
        Utilisateur utilisateur = manager.find(Utilisateur.class, utilisateurId);
        manager.remove(utilisateur);
        manager.getTransaction().commit();
    }

    // Récupérer un utilisateur par son identifiant
    public Utilisateur getById(Long utilisateurId) {
        return manager.find(Utilisateur.class, utilisateurId);
    }
}

