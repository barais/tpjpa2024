package dao;

import domain.Patient;

public class PatientDAO extends AbstractJpaDao<Long, Patient> {

    public PatientDAO() { super(Patient.class); }
}
