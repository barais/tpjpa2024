package jpa.dao;

import jpa.domain.Department;
import jpa.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityDAO {
    private EntityManager manager;

    public EntityDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void createEmployees() {
        var numOfEmployees = manager
                .createQuery("Select a From Employee a", Employee.class)
                .getResultList().size();
        if (numOfEmployees == 0) {
            var department = new Department("java");
            manager.persist(department);
            manager.persist(new Employee("Jakab Gipsz",department));
            manager.persist(new Employee("Captain Nemo",department));
        }
    }

    public void listEmployees() {
        var listResults = manager
                .createQuery("Select a From Employee a", Employee.class)
                .getResultList();
        System.out.println("num of employees:" + listResults.size());
        for (var next : listResults) {
            System.out.println("next employee: " + next);
        }
    }

    public static void main(String[] args) {
        var entityManagerFactory = Persistence.createEntityManagerFactory("example");
        var entityManager = entityManagerFactory.createEntityManager();
        var entityDAO = new EntityDAO(entityManager);

        var tx = entityManager.getTransaction();
        tx.begin();
        try {
            entityDAO.createEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        entityDAO.listEmployees();
        entityManager.close();
        System.out.println("... done");
    }
}
