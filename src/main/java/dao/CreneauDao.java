package dao;

import model.Creneau;
import model.Rdv;

import java.util.List;

public class CreneauDao extends AbstractJpaDao<Long,Creneau> {
    public CreneauDao() {
        super(Creneau.class);
    }

}
