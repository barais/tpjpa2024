package jpa.dao;

import jpa.po.Appointment;
import jpa.po.Patient;

import java.util.List;

public class PatientDAO extends GenericJpaDAO<Patient, Long> {

    public PatientDAO() {
        super(Patient.class);
    }

    // Create queries

    public void createPatients(String firstName, String lastName) {
        manager.persist(new Patient(firstName, lastName));
    }

    // Fetch queries

    public Patient getPatientByName(String firstName, String lastName) {
        return manager
                .createQuery("SELECT p FROM Patient p WHERE p.lastName LIKE :lastName AND p.firstName LIKE :firstName", Patient.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList()
                .get(0);
    }

    public List<Patient> getPatientList() {
        return manager
                .createQuery("Select p From Patient p", Patient.class)
                .getResultList();
    }

    // Remove queries

    public void removePatientByName(String firstName, String lastName) {
        this.manager.createQuery("delete from Patient p where p.firstName = :firstName and p.lastName = :lastName")
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
    }

    // Update queries

    public void removeAppointmentToPatient(String firstName, String lastName, Long appointmentId) {
        this.manager.createQuery("delete from Appointment app " +
                "where app.id = :id and app.patient.lastName = :lastName " +
                "and app.patient.firstName = :firstName")
                .setParameter("id", appointmentId)
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
    }
}
