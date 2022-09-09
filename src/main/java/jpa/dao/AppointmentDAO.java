package jpa.dao;

import jpa.po.Appointment;
import jpa.po.Patient;
import jpa.po.Professional;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDAO extends GenericJpaDAO<Appointment, Long> {
    public AppointmentDAO() {
        super(Appointment.class);
    }

    public void addAppointment(String reason, LocalDateTime startingTime, Patient patient, Professional professional) {
        manager.persist(new Appointment(reason, startingTime, patient, professional));
    }
    // Access appointments
    public List<Professional> getAppointmentsByProfessionalId(Long id) {
        return manager.createQuery("SELECT p FROM Professional p WHERE p.id = :id", Professional.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Patient> getAppointmentsByPatientId(Long id) {
        return manager.createQuery("SELECT p FROM Patient p WHERE p.id = :id", Patient.class)
                .setParameter("id", id)
                .getResultList();
    }
    // Create, list, remove and update
}
