package dao;

import models.Label;

public class LabelDao extends AbstractJpaDao<Long, Label> {
    public LabelDao() {
        setClazz(Label.class);
    }
}
