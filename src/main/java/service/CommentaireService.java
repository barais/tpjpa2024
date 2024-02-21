package service;

import dao.CommentaireDao;
import busi.Commentaire;
import dao.FicheDao;
import jakarta.persistence.EntityManager;

public class CommentaireService {
    private final CommentaireDao commentaireDao;

    public CommentaireService(EntityManager manager) {
        this.commentaireDao = new CommentaireDao(manager);
    }

    public void create(Commentaire commentaire) {
        commentaireDao.save(commentaire);
    }

    public void update(Commentaire commentaire) {
        commentaireDao.update(commentaire);
    }

    public void delete(Long commentaireId) {
        commentaireDao.delete(commentaireId);
    }
    public Commentaire getById(Long commentaireId) {
        return commentaireDao.getById(commentaireId);
    }
}
