package jpa.dao;

import jpa.EntityManagerHelper;
import jpa.po.Appointment;
import jpa.po.Professional;

import javax.persistence.EntityManager;

public class AppointmentDAO extends GenericJpaDao<Appointment, Long> {
    public AppointmentDAO() {
        super(Appointment.class);
    }
    // Create, list, remove and update
}
