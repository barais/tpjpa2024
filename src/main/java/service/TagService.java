package service;

import busi.Tag;
import dao.TagDao;
import jakarta.persistence.EntityManager;

public class TagService {
    private TagDao tagDao;

    public TagService(EntityManager manager) {
        this.tagDao = new TagDao(manager);
    }

    // Créer un nouveau tag
    public void create(Tag tag) {
        tagDao.create(tag);
    }

    // Mettre à jour un tag existant
    public void update(Tag tag) {
        tagDao.update(tag);
    }

    // Supprimer un tag existant
    public void delete(Long tagId) {
        tagDao.delete(tagId);
    }

    // Récupérer un tag par son identifiant
    public Tag getById(Long tagId) {
        return tagDao.getById(tagId);
    }
}

