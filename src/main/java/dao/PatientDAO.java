package dao;

import entities.Patient;

public class PatientDAO extends AbstractJpaDao<Long, Patient> {

    public PatientDAO() { super(Patient.class); }
}
