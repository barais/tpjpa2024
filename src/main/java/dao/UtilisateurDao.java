package dao;

import domaine.Utilisateur;

public class UtilisateurDao extends AbstractJpaDao<Long, Utilisateur> {
    public UtilisateurDao(){
        this.setClazz(Utilisateur.class);
    }
}

