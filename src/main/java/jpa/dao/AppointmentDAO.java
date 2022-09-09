package jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppointmentDAO {
    private EntityManager manager;
    public AppointmentDAO(EntityManager manager) {
        this.manager = manager;
    }
}
