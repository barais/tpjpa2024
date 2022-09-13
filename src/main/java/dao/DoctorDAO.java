package dao;

import entities.Doctor;

public class DoctorDAO extends AbstractJpaDao<Long, Doctor>{
    public DoctorDAO(Class<Doctor> clazzToSet) {
        super(Doctor.class);
    }
}
