package dao;

import model.Rdv;

public class RdvDao extends AbstractJpaDao<Long,Rdv> {

    public RdvDao() {
        super(Rdv.class);
    }
}
