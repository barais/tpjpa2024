package dao;

import domain.Specialisation;

public class SpecialisationDAO extends AbstractJpaDao<Long, entities.Specialisation> {

    public SpecialisationDAO() { super(Specialisation.class); }
}
