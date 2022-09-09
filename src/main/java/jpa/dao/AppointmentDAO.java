package jpa.dao;

import jpa.EntityManagerHelper;
import jpa.po.Appointment;
import jpa.po.Professional;

import javax.persistence.EntityManager;

public class AppointmentDAO extends GenericJpaDao<Appointment, Long> {
    public AppointmentDAO() {
        super(Appointment.class);
    }

    // Access appointments

    // Create, list, remove and update
}
