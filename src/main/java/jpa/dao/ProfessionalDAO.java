package jpa.dao;

import jpa.po.Professional;

import java.util.List;

public class ProfessionalDAO extends GenericJpaDAO<Professional, Long> {

    public ProfessionalDAO() {
        super(Professional.class);
    }

    public void createProfessional(String firstName, String lastName) {
        manager.persist(new Professional(firstName, lastName));
    }

    public Professional getPatientByName(String firstName, String lastName) {
        return manager
                .createQuery("SELECT p FROM Professional p WHERE p.lastName LIKE :lastName AND p.firstName LIKE :firstName", Professional.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList()
                .get(0);
    }

    public List<Professional> listProfessionals() {
        return manager
                .createQuery("SELECT p FROM Patient p", Professional.class)
                .getResultList();
    }
    // Create, list, remove and update
}
