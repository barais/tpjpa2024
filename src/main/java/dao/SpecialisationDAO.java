package dao;

import entities.Specialisation;

public class SpecialisationDAO extends AbstractJpaDao<Long, entities.Specialisation> {

    public SpecialisationDAO() { super(Specialisation.class); }
}
