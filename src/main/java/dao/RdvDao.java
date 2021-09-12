package dao;

import model.Rdv;

import java.util.List;

public class RdvDao extends AbstractJpaDao<Long,Rdv> {

    public RdvDao() {
        super(Rdv.class);
    }

    public List<Rdv> findByTitle(String s) {
        return entityManager.createQuery("select e from Rdv as e where e.title ='" +s+ "'", Rdv.class).getResultList();
    }

}
