package dao;

import models.Commentaire;

public class CommentaireDao extends AbstractJpaDao<Long, Commentaire> {
    public CommentaireDao() {
        setClazz(Commentaire.class);
    }
}
