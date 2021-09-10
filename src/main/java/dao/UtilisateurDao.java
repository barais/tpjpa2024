package dao;

import java.util.List;

import javax.persistence.EntityManager;

import domain.Utilisateur;


public class UtilisateurDao {

    private EntityManager manager;

    public UtilisateurDao (EntityManager manager) {
        this.manager = manager;
    }

    public void createUtilisateurs() {
        int numOfUsers = manager.createQuery("Select a From Utilisateur a", Utilisateur.class).getResultList().size();
        if (numOfUsers == 0) {
            manager.persist(new Utilisateur("User 1"));
            manager.persist(new Utilisateur("User 2"));
        }
    }

    public void listUtilisateurs() {
        List<Utilisateur> resultList = manager.createQuery("Select a From Utilisateur a", Utilisateur.class).getResultList();
        System.out.println("Nombre d'utilisateurs : " + resultList.size());
        for (Utilisateur next : resultList) {
            System.out.println("Utilisateur suivant : " + next);
        }
    }


}
