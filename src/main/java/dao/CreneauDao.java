package dao;

import model.Creneau;

public class CreneauDao extends AbstractJpaDao<Long,Creneau> {
    public CreneauDao() {
        super(Creneau.class);
    }
}
