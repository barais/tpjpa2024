package dao;

import entities.Patient;

public class PatientDAO extends AbstractJpaDao<Long, entities.Patient> {

    public PatientDAO() { super(Patient.class); }
}
