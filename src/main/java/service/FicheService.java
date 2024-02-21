package service;

import busi.Fiche;
import dao.FicheDao;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * @autor nana
 */
public class FicheService {
    private final FicheDao ficheDao;

    public FicheService(EntityManager manager) {
        this.ficheDao = new FicheDao(manager);
    }

    // Créer une nouvelle fiche
    public void create(Fiche fiche) {
        ficheDao.save(fiche);
    }

    // Mettre à jour une fiche existante
    public void update(Fiche fiche) {
        ficheDao.update(fiche);
    }

    // Supprimer une fiche existante
    public void delete(Long ficheId) {
        ficheDao.delete(ficheId);
    }

    // Récupérer une fiche par son identifiant
    public Fiche getById(Long ficheId) {
        return ficheDao.getById(ficheId);
    }

    // Récupérer toutes les fiches
    public List<Fiche> getAllFiches() {
        return ficheDao.getAllFiches();
    }
}

