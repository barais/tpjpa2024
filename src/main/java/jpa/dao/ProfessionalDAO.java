package jpa.dao;

import jpa.EntityManagerHelper;
import jpa.po.Professional;

import javax.persistence.EntityManager;

public class ProfessionalDAO extends GenericJpaDao<Professional, Long> {

    public ProfessionalDAO() {
        super(Professional.class);
    }

    // Create, list, remove and update
}
