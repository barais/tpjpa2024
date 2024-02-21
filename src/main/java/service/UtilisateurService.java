package service;

import busi.Utilisateur;
import dao.TicketDao;
import dao.UtilisateurDao;
import jakarta.persistence.EntityManager;

public class UtilisateurService {
    private UtilisateurDao utilisateurDao;

    public UtilisateurService( EntityManager manager) {
        this.utilisateurDao = new UtilisateurDao(manager);
    }

    // Créer un nouvel utilisateur
    public void create(Utilisateur utilisateur) {
        utilisateurDao.create(utilisateur);
    }

    // Mettre à jour un utilisateur existant
    public void update(Utilisateur utilisateur) {
        utilisateurDao.update(utilisateur);
    }

    // Supprimer un utilisateur existant
    public void delete(Long utilisateurId) {
        utilisateurDao.delete(utilisateurId);
    }

    // Récupérer un utilisateur par son identifiant
    public Utilisateur getById(Long utilisateurId) {

        return utilisateurDao.getById(utilisateurId);
    }
}

