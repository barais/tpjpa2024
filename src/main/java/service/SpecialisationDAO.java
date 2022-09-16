package service;

import domain.Specialisation;

public class SpecialisationDAO extends AbstractJpaDao<Long, Specialisation> {

    public SpecialisationDAO() { super(Specialisation.class); }
}
