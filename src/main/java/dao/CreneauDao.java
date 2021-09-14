package dao;

import model.Creneau;
import model.Rdv;

import java.util.List;

public class CreneauDao extends AbstractJpaDao<Long,Creneau> {
    public CreneauDao() {
        super(Creneau.class);
    }

    public List<Rdv> findByCrenel(long id) {
        return entityManager.createQuery("select e from Rdv as e where e.creneau.id ='" +id+ "'", Rdv.class).getResultList();
    }
}
