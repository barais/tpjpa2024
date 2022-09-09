package jpa.dao;

import jpa.po.Professional;

import java.util.List;

public class ProfessionalDAO extends GenericJpaDAO<Professional, Long> {

    public ProfessionalDAO() {
        super(Professional.class);
    }

    // Create

    public void createProfessional(String firstName, String lastName) {
        manager.persist(new Professional(firstName, lastName));
    }

    // Get

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

    // Remove queries

    public void removeProfessionalByName(String firstName, String lastName) {
        this.manager.createQuery("delete from Professional p where p.firstName = :firstName and p.lastName = :lastName")
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
    }
}
